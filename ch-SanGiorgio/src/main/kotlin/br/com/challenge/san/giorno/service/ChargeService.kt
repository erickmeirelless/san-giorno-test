package br.com.challenge.san.giorno.service

import br.com.challenge.san.giorno.dto.ChargeEntity
import br.com.challenge.san.giorno.dto.ChargeRequest
import br.com.challenge.san.giorno.repository.ChargeRepository
import org.springframework.stereotype.Service

@Service
class ChargeService(private val chargeRepository: ChargeRepository) {

    fun createCharge(request: ChargeRequest): ChargeEntity {
        val chargeEntity = ChargeEntity(
            id= 0,
            code = request.code,
            originalAmount = request.originalAmount
        )

        return chargeRepository.save(chargeEntity)
    }
}
