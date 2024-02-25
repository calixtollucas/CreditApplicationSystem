package com.rukadev.credit.application.system.exceptions

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice // avisa ao spring que esta classe irá tratar os erros
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class) //indica que este método irá escutar um determinado Exception
    fun handlerValidException(ex: MethodArgumentNotValidException):ResponseEntity<ExceptionDetails>{
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream()
                .forEach { e: ObjectError ->
                    val fieldName: String = (e as FieldError).field
                    errors.put(fieldName, e.defaultMessage)

                }
        return ResponseEntity(
                ExceptionDetails(
                        title= "Bad Request: Consult the documentation",
                        timestamp= LocalDateTime.now(),
                        status = HttpStatus.BAD_REQUEST.value(),
                        exception = ex.objectName,
                        details = errors),
                HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(DataAccessException::class) //indica que este método irá escutar um determinado Exception
    fun handlerValidException(ex: DataAccessException):ResponseEntity<ExceptionDetails>{

        return ResponseEntity(
                ExceptionDetails(
                        title= "Bad Request: Consult the documentation",
                        timestamp= LocalDateTime.now(),
                        status = HttpStatus.CONFLICT.value(),
                        exception = ex.javaClass.toString(),
                        details = mutableMapOf(ex.cause.toString() to ex.message)),
                HttpStatus.CONFLICT)
    }

    @ExceptionHandler(BusinessException::class) //indica que este método irá escutar um determinado Exception
    fun handlerValidException(ex: BusinessException):ResponseEntity<ExceptionDetails>{

        return ResponseEntity(
                ExceptionDetails(
                        title= "Bad Request: Consult the documentation",
                        timestamp= LocalDateTime.now(),
                        status = HttpStatus.CONFLICT.value(),
                        exception = ex.javaClass.toString(),
                        details = mutableMapOf(ex.cause.toString() to ex.message)),
                HttpStatus.CONFLICT)
    }
}