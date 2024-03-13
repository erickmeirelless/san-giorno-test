package br.com.challenge.san.giorno.dto

data class ChargeResponse(
    val id: Long = 0,
    val code: String = "",
    val originalAmount: Double = 0.0
)