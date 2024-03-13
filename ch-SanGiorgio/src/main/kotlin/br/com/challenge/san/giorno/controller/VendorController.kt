package br.com.challenge.san.giorno.controller

import br.com.challenge.san.giorno.dto.VendorRequest
import br.com.challenge.san.giorno.dto.VendorResponse
import br.com.challenge.san.giorno.service.VendorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vendor")
class `VendorController`(private val VendorService: VendorService) {
    @PostMapping("/validate")
    fun validarVendor(@RequestBody request: VendorRequest): ResponseEntity<VendorResponse> {
        val isValid = VendorService.validateVendor(request.code)
        return if (isValid) ResponseEntity.ok(VendorResponse(true, "")) else ResponseEntity.badRequest()
            .body(VendorResponse(false, ""))
    }
}