package br.com.challenge.san.giorno.dto

data class ChargeRequest(
    val code: String = "",
    val originalAmount: Double = 0.0
)