package com.rukadev.credit.application.system.repository

import com.rukadev.credit.application.system.model.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository //um Bean que será gerenciado pelo Spring. Se a classe extende JpaRepository não é necessário
interface CreditRepository: JpaRepository<Credit, Long> {

    @Query(value="SELECT * FROM CREDIT WHERE CREDIT_CODE = ?1", nativeQuery = true)
    fun findByCreditCode(creditCode: UUID): Credit?

    @Query(value="SELECT * FROM CREDIT WHERE CUSTOMER_ID = ?1", nativeQuery = true)
    fun findAllByCustomerId(customerId: Long): List<Credit>
}