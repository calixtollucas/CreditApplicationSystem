package com.rukadev.credit.application.system.service.impl

import com.rukadev.credit.application.system.exceptions.BusinessException
import com.rukadev.credit.application.system.model.Customer
import com.rukadev.credit.application.system.repository.CustomerRepository
import com.rukadev.credit.application.system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService {
    override fun save(customer: Customer): Customer {
        return this.customerRepository.save(customer);
    }

    override fun findById(id: Long): Customer {
        return this.customerRepository.findById(id).orElseThrow { throw BusinessException("Id $id not found") };
    }

    override fun deleteById(id: Long){

        val customer = customerRepository.findById(id).orElseThrow{BusinessException("Delete not executed, customer not exists")}
        this.customerRepository.delete(customer);
    }
}