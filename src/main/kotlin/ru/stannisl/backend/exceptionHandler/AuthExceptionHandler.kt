package ru.stannisl.backend.exceptionHandler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.stannisl.backend.exceptionHandler.exceptions.CredentialsMismatchException
import ru.stannisl.backend.exceptionHandler.exceptions.TableEntityNotFoundException
import ru.stannisl.backend.models.response.ErrorResponse

@ControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(CredentialsMismatchException::class)
    fun handleCredentialMismatch(e: CredentialsMismatchException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(e.message!!))

    @ExceptionHandler(TableEntityNotFoundException::class)
    fun handleTableEntityNotFound(e: TableEntityNotFoundException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(e.message!!))
}