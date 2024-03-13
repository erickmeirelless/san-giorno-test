package br.com.challenge.san.giorno.unit

import br.com.challenge.san.giorno.localstack.PostgreSQLContainerIntegrationTest
import br.com.challenge.san.giorno.Application
import br.com.challenge.san.giorno.entity.VendorEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import br.com.challenge.san.giorno.repository.VendorRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [PostgreSQLContainerIntegrationTest::class, Application::class])
class VendorRepositoryTest {

    @Autowired
    private lateinit var vendorRepository: VendorRepository

    @BeforeEach
    fun init(){
        vendorRepository.save(VendorEntity( 0,"123"))
    }

    @Test
    fun `test findByCode`() {
        val vendor = vendorRepository.findByCode("123")
        assertNotNull(vendor)
        assertEquals("123", vendor.get().code)
    }
}
