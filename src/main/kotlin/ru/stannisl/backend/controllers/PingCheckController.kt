package ru.stannisl.backend.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class PingCheckController {
    @GetMapping("/ping")
    fun ping() = mapOf("status" to "ok")
}