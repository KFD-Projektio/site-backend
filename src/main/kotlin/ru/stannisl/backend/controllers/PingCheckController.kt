package ru.stannisl.backend.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ping")
class PingCheckController : BaseApiController() {
    @GetMapping
    fun ping() = mapOf("status" to "ok")
}
