package ru.projektio.backend.exceptionHandler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.projektio.backend.exceptionHandler.exceptions.CredentialsMismatchException
import ru.projektio.backend.exceptionHandler.exceptions.TableEntityNotFoundException
import ru.projektio.backend.models.response.ErrorResponse

@ControllerAdvice
class AuthExceptionHandler {

    @ExceptionHandler(ru.projektio.backend.exceptionHandler.exceptions.CredentialsMismatchException::class)
    fun handleCredentialMismatch(e: ru.projektio.backend.exceptionHandler.exceptions.CredentialsMismatchException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(e.message!!))

    @ExceptionHandler(ru.projektio.backend.exceptionHandler.exceptions.TableEntityNotFoundException::class)
    fun handleTableEntityNotFound(e: ru.projektio.backend.exceptionHandler.exceptions.TableEntityNotFoundException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(e.message!!))
}