package br.com.challenge.san.giorno.repository

import br.com.challenge.san.giorno.entity.VendorEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface VendorRepository : JpaRepository<VendorEntity, Long> {
    fun findByCode(code: String): Optional<VendorEntity>
}


