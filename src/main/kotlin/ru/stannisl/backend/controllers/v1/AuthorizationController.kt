package ru.stannisl.backend.controllers.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.stannisl.backend.models.requests.user.AuthUserRequest
import ru.stannisl.backend.models.response.jwtTokenResponse.AuthTokensResponse
import ru.stannisl.backend.service.AuthService

@RestController
@RequestMapping("/v1/auth")
class AuthorizationController(
    private val authService: AuthService
) {
    @RequestMapping("/login")
    fun login(@RequestBody authRequestBody: AuthUserRequest): ResponseEntity<AuthTokensResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(authService.authUser(authRequestBody))


//    @RequestMapping("/refresh")
//    fun refreshAccessToken(@RequestBody refreshTokenRequest: RefreshTokenRequest): ResponseEntity<AuthTokensResponse> =
//        ResponseEntity
//            .status(HttpStatus.OK)
//            .body(AuthTokensResponse("none", "none"))
//
}