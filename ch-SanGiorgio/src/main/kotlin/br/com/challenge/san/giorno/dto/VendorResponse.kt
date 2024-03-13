package br.com.challenge.san.giorno.dto

data class VendorResponse(
    var isValid: Boolean = false,
    var message: String = "",
    var timestamp: Long? = null
)