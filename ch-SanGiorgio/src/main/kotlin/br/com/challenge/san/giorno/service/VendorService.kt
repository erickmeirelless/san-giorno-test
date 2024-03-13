package br.com.challenge.san.giorno.service

import br.com.challenge.san.giorno.repository.VendorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VendorService(private val vendorRepository: VendorRepository) {
     fun validateVendor(codigo: String): Boolean {
        return vendorRepository.findByCode(codigo).isPresent
    }
}


