package com.rukadev.credit.application.system.dto

import com.rukadev.credit.application.system.model.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdatedDto(
        @field:NotEmpty(message= "First Name Cannot be null")val firstName:String,
        @field:NotEmpty(message= "First Name Cannot be null")val lastName:String,
        @field: NotNull(message= "Cannot be null") val income:BigDecimal,
        @field:NotEmpty(message= "First Name Cannot be null")val zipCode:String,
        @field:NotEmpty(message= "First Name Cannot be null")val street:String
) {

    fun toEntity(customer: Customer): Customer{
        customer.firstName = this.firstName;
        customer.lastName = this.lastName;
        customer.income = this.income;
        customer.address.zipCode = this.zipCode;
        customer.address.street = this.zipCode;

        return customer;
    }
}
