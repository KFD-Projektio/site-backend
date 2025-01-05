package ru.projektio.backend.controllers.v1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Контроллер для обработки запросов проверки пинга.
 */
@RestController
@RequestMapping("/v1/ping")
class PingCheckController {
    /**
     * Конечная точка для проверки пинга.
     *
     * @return ResponseEntity со статусом OK и телом, содержащим карту с ключом "status" и значением "ok".
     */
    @GetMapping
    fun ping() = mapOf("status" to "ok")
}
