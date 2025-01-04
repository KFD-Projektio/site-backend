package ru.projektio.backend.controllers.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.projektio.backend.models.requests.user.AuthUserRequest
import ru.projektio.backend.models.response.jwtTokenResponse.AuthTokensResponse
import ru.projektio.backend.service.AuthService

@RestController
@RequestMapping("/v1/auth")
class AuthorizationController(
    private val authService: ru.projektio.backend.service.AuthService
) {
    @RequestMapping("/login")
    fun login(@RequestBody authRequestBody: ru.projektio.backend.models.requests.user.AuthUserRequest): ResponseEntity<AuthTokensResponse> =
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