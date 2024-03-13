package br.com.challenge.san.giorno.service

import br.com.challenge.san.giorno.dto.PaymentRequest
import br.com.challenge.san.giorno.repository.ChargeRepository
import br.com.challenge.san.giorno.repository.PaymentRepository
import br.com.challenge.san.giorno.repository.VendorRepository
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val vendorRepository: VendorRepository,
    private val chargeRepository: ChargeRepository,
    private val paymentRepository: PaymentRepository,
    private val sqsEventPublisher: SQSEventPublisher
) {
    fun processarPayments(paymentDTO: PaymentRequest): PaymentRequest {
        val vendor = vendorRepository.findByCode(paymentDTO.vendorCode)

        paymentDTO.payments.forEach { paymentInfo ->
            val charge = chargeRepository.findByCode(paymentInfo.chargeCode)
                ?: throw IllegalArgumentException("charge not found")

            val paymentStatus = determinePaymentStatus(paymentInfo.amountPay, charge.originalAmount)

            sendToSqsQueue(paymentDTO, paymentStatus)
        }

        return paymentDTO
    }

    private fun determinePaymentStatus(amountPay: Double, originalAmount: Double): String {
        return when {
            amountPay < originalAmount -> "partial"
            amountPay == originalAmount -> "total"
            else -> "over"
        }
    }

    private fun sendToSqsQueue(paymentDTO:PaymentRequest, paymentStatus: String) {

        val queueName = StringBuffer("").append("queue-").append(paymentStatus).toString()
        sqsEventPublisher.publishEvent(queueName, paymentDTO)
    }
}