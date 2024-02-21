package com.rukadev.credit.application.system.service

import com.rukadev.credit.application.system.model.Customer


interface ICustomerService {
    fun save(customer: Customer): Customer;
    fun findById(id: Long): Customer
    fun deleteById(id: Long)
}