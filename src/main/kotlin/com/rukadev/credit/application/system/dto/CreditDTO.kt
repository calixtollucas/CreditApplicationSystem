package com.rukadev.credit.application.system.dto

import com.rukadev.credit.application.system.model.Credit
import com.rukadev.credit.application.system.model.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(
    @field:NotNull val creditValue: BigDecimal,
    @field:Future() val dayOfFirstInstallment: LocalDate,

    @field:Min(1)
    @field:Max(48)
    val numberOfInstallments: Int,
    @field:NotNull val customerId:Long
) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dateFirstInstallmant = this.dayOfFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
        )
}
