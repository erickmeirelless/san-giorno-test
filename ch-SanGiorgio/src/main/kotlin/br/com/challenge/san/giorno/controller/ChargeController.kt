package br.com.challenge.san.giorno.controller

import br.com.challenge.san.giorno.dto.ChargeEntity
import br.com.challenge.san.giorno.dto.ChargeRequest
import br.com.challenge.san.giorno.dto.ChargeResponse
import br.com.challenge.san.giorno.service.ChargeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/charge")
class ChargeController(private val chargeService: ChargeService) {

    @PostMapping("/create")
    fun createCharge(@RequestBody request: ChargeRequest): ResponseEntity<ChargeEntity> {
        val charge = chargeService.createCharge(request)
        return ResponseEntity.ok(charge)
    }
}