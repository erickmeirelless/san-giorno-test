package br.com.challenge.san.giorno.repository

import br.com.challenge.san.giorno.entity.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<PaymentEntity, Long> {
    fun findByChargeId(chargeId: Long): List<PaymentEntity>
}
