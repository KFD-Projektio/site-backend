package ru.stannisl.backend.controllers

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.stannisl.backend.service.UserService

@RestController
@RequestMapping("/auth")
class AuthorizationController(
    val service: UserService,
) : BaseApiController() {}