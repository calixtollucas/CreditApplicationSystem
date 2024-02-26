package com.rukadev.credit.application.system.service

import com.rukadev.credit.application.system.exceptions.BusinessException
import com.rukadev.credit.application.system.model.Address
import com.rukadev.credit.application.system.model.Customer
import com.rukadev.credit.application.system.repository.CustomerRepository
import com.rukadev.credit.application.system.service.impl.CustomerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.stubbing.OngoingStubbing
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*
import java.util.Random

@ActiveProfiles("test")
@ExtendWith(MockitoExtension::class)
class CustomerServiceTest {
    @Mock lateinit var customerRepository: CustomerRepository
    @InjectMocks lateinit var customerService: CustomerService

    @Test
    fun `should create customer`(){
        //given - dados que precisamos receber para executar o teste
        val fakeCustomer: Customer = buildFakeCustomer()
        Mockito.`when`(customerRepository.save(ArgumentMatchers.any(Customer::class.java))).thenReturn(fakeCustomer)
        //when - onde ocorrerá os métodos de fato
        val actual:Customer = customerService.save(fakeCustomer)

        //then - valores que esperamos (assertivas)
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        Mockito.`verify`(fakeCustomer, Mockito.times(1))
    }

    @Test
    fun `should find customer by id`(){
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildFakeCustomer(id = fakeId);

        Mockito.`when`(customerRepository.findById(fakeId)).thenReturn(Optional.of(fakeCustomer))
        //when
        val actual:Customer  = customerService.findById(fakeId)
        //then

        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
    }

    @Test
    fun `should not find customer by id and throw a BusinessException`(){
        //given
        val fakeId: Long = Random().nextLong()

        Mockito.`when`(customerRepository.findById(fakeId)).thenReturn(Optional.empty())
        //when
        //then

        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId not found")
    }

    @Test
    fun `should delete customer by id`(){
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = buildFakeCustomer(id = fakeId);

        Mockito.`when`(customerRepository.findById(fakeId)).thenReturn(Optional.of(fakeCustomer))
        Mockito.doNothing().`when`(customerRepository).delete(fakeCustomer)
        //when
        customerService.deleteById(fakeId)
        //then
        Mockito.`verify`(customerRepository, Mockito.times(1)).findById(fakeId)
        Mockito.`verify`(customerRepository, Mockito.times(1)).delete(fakeCustomer)

    }

    private fun buildFakeCustomer(
        firstName: String = "Lucas",
        lastName:String = "Calixto",
        cpf: String = "48029382090",
        email: String = "lucas123@gmail.com",
        password: String = "lucas123",
        zipCode: String = "12345",
        street: String = "Rua das seringueiras",
        income: BigDecimal = BigDecimal.valueOf(3500),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        income = income,
        password = password,
        address = Address(
            zipCode,
            street
        ),
        id = id
    )
}