package com.rukadev.credit.application.system.dto

import com.rukadev.credit.application.system.model.Customer
import java.math.BigDecimal

data class CustomerView(
    val firstName:String,
    val lastName:String,
    val cpf:String,
    val income:BigDecimal,
    val email:String,
    val zipCode:String,
    val street:String,
    val id: Long?
) {

    constructor(customer: Customer): this(
        customer.firstName,
        customer.lastName,
        customer.cpf,
        customer.income,
        customer.email,
        customer.address.zipCode,
        customer.address.street,
        customer.id
    )

}
