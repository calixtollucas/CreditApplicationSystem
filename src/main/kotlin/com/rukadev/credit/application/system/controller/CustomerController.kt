package com.rukadev.credit.application.system.controller

import com.rukadev.credit.application.system.dto.CustomerDTO
import com.rukadev.credit.application.system.dto.CustomerUpdatedDto
import com.rukadev.credit.application.system.dto.CustomerView
import com.rukadev.credit.application.system.model.Customer
import com.rukadev.credit.application.system.service.impl.CustomerService
import jakarta.validation.Valid
import jakarta.websocket.server.PathParam
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(
    private val customerService: CustomerService
) {

    //anotação indicando que é um método post
    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDTO: CustomerDTO): ResponseEntity<CustomerView> {

        val saved = this.customerService.save(customerDTO.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerView(saved))
    }

    @GetMapping("/{customer_id}")
    fun getCustomerById(@PathVariable customer_id: Long): ResponseEntity<CustomerView> {
        val customer = this.customerService.findById(customer_id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(customer));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long):ResponseEntity<String>{
        this.customerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deletado!")
    }

    @PatchMapping
    fun updateCustomer(@PathParam(value = "customer_id") customer_id:Long,
                       @RequestBody @Valid customerUpdatedDto: CustomerUpdatedDto): ResponseEntity<CustomerView>{
        val customerFind: Customer = this.customerService.findById(customer_id);
        val customerToUpdate = customerUpdatedDto.toEntity(customerFind);

        return ResponseEntity.status(HttpStatus.OK).body(CustomerView(this.customerService.save(customerToUpdate)));
    }
}