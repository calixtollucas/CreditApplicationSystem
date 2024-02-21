package com.rukadev.credit.application.system.service.impl

import com.rukadev.credit.application.system.model.Credit
import com.rukadev.credit.application.system.repository.CreditRepository
import com.rukadev.credit.application.system.service.ICreditService
import java.util.*

class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        //validacao se o customer vinculado ao crédito existe no db
        credit.apply { this.customer_Id = customerService.findById(this.customer_Id
            ?.id!!); }

        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomerId(customerId: Long): List<Credit> = creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId:Long , creditCode: UUID): Credit {
        val credit:Credit = this.creditRepository.findByCreditCode(creditCode)
            ?: throw RuntimeException("creditCode $creditCode not found")

        return if (credit.customer_Id?.id == credit.id) credit else throw RuntimeException("Contact Admin")

    }


}