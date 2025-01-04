package ru.stannisl.backend.controllers.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.stannisl.backend.models.requests.AuthUserRequest
import ru.stannisl.backend.models.response.AuthUserResponse
import ru.stannisl.backend.service.UserService

@RestController
@RequestMapping("/v1/auth")
class AuthorizationController(
    val userService: UserService,
) {
    @RequestMapping("/login")
    fun login(@RequestBody authRequestBody: AuthUserRequest): ResponseEntity<AuthUserResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.authorizeUser(authRequestBody))
}