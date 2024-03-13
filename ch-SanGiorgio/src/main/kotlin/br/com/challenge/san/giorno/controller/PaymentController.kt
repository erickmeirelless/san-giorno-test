package br.com.challenge.san.giorno.controller

import br.com.challenge.san.giorno.dto.PaymentRequest
import br.com.challenge.san.giorno.service.PaymentService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus

@RestController
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping("/payments")
    fun processPayment(@RequestBody paymentDTO: PaymentRequest): ResponseEntity<out Any?> {
        try {
            val result = paymentService.processarPayments(paymentDTO)
            return ResponseEntity(result, HttpStatus.OK)
        } catch (e: Exception) {
            return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }
}