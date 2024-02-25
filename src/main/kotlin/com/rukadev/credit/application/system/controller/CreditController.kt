package com.rukadev.credit.application.system.controller

import com.rukadev.credit.application.system.dto.CreditDTO
import com.rukadev.credit.application.system.dto.CreditView
import com.rukadev.credit.application.system.dto.CreditViewList
import com.rukadev.credit.application.system.model.Credit
import com.rukadev.credit.application.system.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credit")
class CreditController(
    private val creditService: CreditService
) {

    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDTO): ResponseEntity<String>{
        val creditSaved: Credit = this.creditService.save(creditDto.toEntity());

        return ResponseEntity.status(HttpStatus.CREATED).body("Credit ${creditSaved.creditCode} -> Customer ${creditSaved.customer?.firstName} saved!")
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>>{
        val creditList: List<Credit> = this.creditService.findAllByCustomerId(customerId);

        return ResponseEntity.status(HttpStatus.OK).body(creditList.stream()
                .map { c -> CreditViewList(c) }
                .collect(Collectors.toList()))
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "customerId") customerId: Long, /* api/credit?customerId=1/4 */
                         @PathVariable creditCode: UUID): ResponseEntity<CreditView> {

        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)

        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit));
    }

}