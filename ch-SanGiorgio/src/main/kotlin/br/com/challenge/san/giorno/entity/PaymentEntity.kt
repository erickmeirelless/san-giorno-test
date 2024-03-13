package br.com.challenge.san.giorno.entity

import jakarta.persistence.*

@Entity
@Table(name = "payment")
class PaymentEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var chargeId: Long = 0,
    var amountPayed: Double = 0.0
)