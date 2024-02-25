package com.rukadev.credit.application.system.dto

import com.rukadev.credit.application.system.model.Address
import com.rukadev.credit.application.system.model.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDTO(
        @field:NotEmpty(message= "First Name Cannot be null") val firstName:String,

        @field:NotEmpty(message= "Last Name Cannot be null") val lastName:String,

        @field:NotEmpty(message= "CPF Cannot be null")
        @field:CPF
        val cpf:String,

        @field:NotNull(message="Cannot be null") val income:BigDecimal,

        @field:NotEmpty(message= "email Cannot be null")
        @Email
        val email:String,

        @field:NotEmpty(message= "password Cannot be null") val password:String,

        @field:NotEmpty(message= "Zip Code Cannot be null") val zipCode:String,

        @field:NotEmpty(message= "Street Cannot be null") val street:String
) {
    fun toEntity():Customer = Customer(this.firstName,
            this.lastName,
            this.cpf,
            this.email,
            this.income,
            this.password,
            Address(
                this.zipCode,
                this.street)
        );
}
