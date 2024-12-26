package ru.stannisl.backend.controllers.v1

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/ping")
class PingCheckController {
    @GetMapping
    fun ping() = mapOf("status" to "ok")
}
