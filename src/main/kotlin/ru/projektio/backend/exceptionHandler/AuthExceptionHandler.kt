package ru.projektio.backend.exceptionHandler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import ru.projektio.backend.exceptionHandler.exceptions.CredentialsMismatchException
import ru.projektio.backend.exceptionHandler.exceptions.NotUniqueRegisterDataException
import ru.projektio.backend.exceptionHandler.exceptions.TableEntityNotFoundException
import ru.projektio.backend.models.response.ErrorResponse

/**
 * Контроллер для обработки исключений, связанных с аутентификацией.
 */
@ControllerAdvice
class AuthExceptionHandler {

    /**
     * Обрабатывает исключение CredentialsMismatchException.
     *
     * @param e Исключение CredentialsMismatchException.
     * @return ResponseEntity с кодом состояния UNAUTHORIZED и телом ErrorResponse, содержащим сообщение об ошибке.
     */
    @ExceptionHandler(CredentialsMismatchException::class)
    fun handleCredentialMismatch(e: CredentialsMismatchException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(e.message!!))

    /**
     * Обрабатывает исключение TableEntityNotFoundException.
     *
     * @param e Исключение TableEntityNotFoundException.
     * @return ResponseEntity с кодом состояния UNAUTHORIZED и телом ErrorResponse, содержащим сообщение об ошибке.
     */
    @ExceptionHandler(TableEntityNotFoundException::class)
    fun handleTableEntityNotFound(e: TableEntityNotFoundException): ResponseEntity<ErrorResponse> =
        ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(e.message!!))

    @ExceptionHandler(NotUniqueRegisterDataException::class)
    fun handleNotUniqueDataException(e: NotUniqueRegisterDataException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse(e.message!!))
}

