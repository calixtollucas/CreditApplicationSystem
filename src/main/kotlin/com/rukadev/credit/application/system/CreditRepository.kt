package com.rukadev.credit.application.system

import com.rukadev.credit.application.system.model.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository //um Bean que será gerenciado pelo Spring. Se a classe extende JpaRepository não é necessário
interface CreditRepository: JpaRepository<Credit, Long> {

}