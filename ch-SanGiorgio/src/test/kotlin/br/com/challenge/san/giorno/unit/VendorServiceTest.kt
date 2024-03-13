package br.com.challenge.san.giorno.unit

import br.com.challenge.san.giorno.entity.VendorEntity
import br.com.challenge.san.giorno.repository.VendorRepository
import br.com.challenge.san.giorno.service.VendorService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.util.*

class VendorServiceTest{
    private val vendorRepository = Mockito.mock(VendorRepository::class.java)
    private var vendorService = VendorService(vendorRepository)


    @Test
    fun `test validarVendor`() {
        val code = "123"

        Mockito.`when`(vendorRepository.findByCode(code)).thenReturn(Optional.of(VendorEntity(0,code)))

        val result = vendorService.validateVendor(code)

        assert(result)
    }
}
