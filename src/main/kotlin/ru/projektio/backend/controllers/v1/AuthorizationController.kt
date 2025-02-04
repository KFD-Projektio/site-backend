package ru.projektio.backend.controllers.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.projektio.backend.models.requests.jwtTokenRequests.RefreshTokenRequest
import ru.projektio.backend.models.requests.user.AuthUserRequest
import ru.projektio.backend.models.requests.user.RegisterUserRequest
import ru.projektio.backend.models.response.jwtTokenResponse.AuthTokensResponse
import ru.projektio.backend.models.response.userResponse.RegisterUserResponse
import ru.projektio.backend.service.AuthService
import ru.projektio.backend.service.UserService


/**
 * Контроллер для обработки запросов, связанных с авторизацией.
 */
@RestController
@RequestMapping("/v1/auth")
class AuthorizationController(
    private val authService: AuthService,
    private val userService: UserService
) {
    /**
     * Конечная точка для входа пользователя.
     *
     * @param authRequestBody Тело запроса, содержащее учетные данные пользователя.
     * @return ResponseEntity со статусом CREATED и телом, содержащим токены аутентификации.
     */
    @RequestMapping("/login")
    fun login(@RequestBody authRequestBody: AuthUserRequest): ResponseEntity<AuthTokensResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(authService.authUser(authRequestBody))

    @PostMapping("/refresh")
    fun refreshToken(@RequestBody request: RefreshTokenRequest): ResponseEntity<AuthTokensResponse>
        = ResponseEntity.ok(authService.refreshAccessToken(request))

    @PostMapping("/register")
    fun create(@RequestBody request: RegisterUserRequest): ResponseEntity<RegisterUserResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.createUser(request))

}
