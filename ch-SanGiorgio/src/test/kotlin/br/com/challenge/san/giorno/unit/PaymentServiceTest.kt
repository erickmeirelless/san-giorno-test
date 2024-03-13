package br.com.challenge.san.giorno.unit

import br.com.challenge.san.giorno.dto.ChargeEntity
import br.com.challenge.san.giorno.dto.PaymentRequest
import br.com.challenge.san.giorno.entity.PaymentEntity
import br.com.challenge.san.giorno.entity.VendorEntity
import br.com.challenge.san.giorno.repository.ChargeRepository
import br.com.challenge.san.giorno.repository.PaymentRepository
import br.com.challenge.san.giorno.repository.VendorRepository
import br.com.challenge.san.giorno.dto.PaymentInfoDTO
import br.com.challenge.san.giorno.service.PaymentService
import br.com.challenge.san.giorno.service.SQSEventPublisher
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.verify
import java.util.*

class PaymentServiceTest {

    private val vendorRepository = Mockito.mock(VendorRepository::class.java)
    private val chargerRepository = Mockito.mock(ChargeRepository::class.java)
    private val paymentRepository = Mockito.mock(PaymentRepository::class.java)
    private val sqsEventPublisher = Mockito.mock(SQSEventPublisher::class.java)

    private val paymentService = PaymentService(vendorRepository, chargerRepository, paymentRepository, sqsEventPublisher )

    @Test
    fun `test processarPayments`() {
        Mockito.`when`(vendorRepository.findByCode("vendor1")).thenReturn(Optional.of(VendorEntity(0,"vendor1")))
        Mockito.`when`(chargerRepository.findByCode("charger1")).thenReturn(ChargeEntity(1, "charger1", 100.0))
        Mockito.`when`(paymentRepository.findByChargeId(1)).thenReturn(listOf(PaymentEntity(1, 1, 50.0)))

        val paymentDTO = PaymentRequest("vendor1", listOf(PaymentInfoDTO("charger1", 50.0)))
        val result = paymentService.processarPayments(paymentDTO)

        assertEquals("vendor1", result.vendorCode)
        assertEquals(1, result.payments.size)
        assertEquals("charger1", result.payments[0].chargeCode)
        assertEquals(50.0, result.payments[0].amountPay)

    }
}
