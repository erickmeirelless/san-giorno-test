package br.com.challenge.san.giorno.repository

import br.com.challenge.san.giorno.dto.ChargeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ChargeRepository  : JpaRepository<ChargeEntity, Long> {
    fun findByCode(code: String): ChargeEntity?
}

