package ru.stannisl.backend.controllers.v1

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.stannisl.backend.service.UserService

@RestController
@RequestMapping("/v1/auth")
class AuthorizationController(
    val service: UserService,
) {

}