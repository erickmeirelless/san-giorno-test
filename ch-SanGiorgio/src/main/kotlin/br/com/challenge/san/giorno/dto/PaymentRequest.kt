package br.com.challenge.san.giorno.dto

data class PaymentRequest(
    val vendorCode: String = "",
    val payments: List<PaymentInfoDTO> = listOf()
)