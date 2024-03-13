package br.com.challenge.san.giorno.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.io.Serializable

import jakarta.persistence.*

@Entity
@Table(name = "vendor")
class VendorEntity (){
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0

        var code: String? = null

        constructor(code: String) : this() {
            this.code = code
        }

        constructor(id: Long, code: String) : this() {
            this.code = code
            this.id = id;
        }
}