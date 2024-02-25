package com.rukadev.credit.application.system.service.impl

import com.rukadev.credit.application.system.exceptions.BusinessException
import com.rukadev.credit.application.system.model.Credit
import com.rukadev.credit.application.system.repository.CreditRepository
import com.rukadev.credit.application.system.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        //validacao se o customer vinculado ao crédito existe no db
        credit.apply { this.customer = customerService.findById(this.customer
            ?.id!!); }

        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomerId(customerId: Long): List<Credit> = creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId:Long , creditCode: UUID): Credit {
        val credit: Credit = this.creditRepository.findByCreditCode(creditCode) //busca o credito
            ?: throw BusinessException("creditCode $creditCode not found")

        return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Contact Admin") //retorna o credito caso o customer_id seja igual ao customer

    }


}