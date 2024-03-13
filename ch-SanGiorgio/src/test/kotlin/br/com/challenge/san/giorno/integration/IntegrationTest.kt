package br.com.challenge.san.giorno.integration

import br.com.challenge.san.giorno.dto.*
import br.com.challenge.san.giorno.entity.VendorEntity
import br.com.challenge.san.giorno.repository.VendorRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

    @Autowired
    private lateinit var vendorRepository: VendorRepository

    @BeforeEach
    fun init(){
        vendorRepository.save(VendorEntity( 0,"vendor123"))
    }

    @Test
    fun `test create vendor, charge, and process payment`() {
        val vendorResponse = restTemplate.postForEntity(
            "/vendor/validate",
            VendorRequest("vendor123", ArrayList()),
            VendorResponse::class.java
        )
        assertThat(vendorResponse.statusCode).isEqualTo(HttpStatus.OK)

        val chargeResponse = restTemplate.postForEntity(
            "/charge/create",
            ChargeRequest("charge123", 100.0),
            ChargeEntity::class.java
        )
        assertThat(chargeResponse.statusCode).isEqualTo(HttpStatus.OK)

        val paymentRequest = PaymentRequest(
            "vendor123",
            listOf(PaymentInfoDTO("charge123", 100.0))
        )
        val paymentResponse = restTemplate.postForEntity(
            "/payments",
            paymentRequest,
            String::class.java
        )
        assertThat(paymentResponse.statusCode).isEqualTo(HttpStatus.OK)

    }
}
