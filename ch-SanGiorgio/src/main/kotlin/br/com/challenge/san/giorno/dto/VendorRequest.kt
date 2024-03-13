package br.com.challenge.san.giorno.dto

import java.io.Serializable

data class VendorRequest(
    val code: String = "",
    val payments: List<PaymentRequest> = listOf()
) : Serializable {
    companion object {
        fun default(): VendorRequest = VendorRequest()
    }
}