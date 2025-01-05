package ru.projektio.backend.service

import ru.projektio.backend.models.requests.jwtTokenRequests.RefreshTokenRequest
import ru.projektio.backend.models.requests.user.AuthUserRequest
import ru.projektio.backend.models.response.jwtTokenResponse.AuthTokensResponse

/**
 * Методы для обработки запросов пользователя на эндпоинт auth
 */
interface AuthService {
    fun authUser(authRequest: AuthUserRequest): AuthTokensResponse
    fun refreshAccessToken(refreshTokenRequest: RefreshTokenRequest): AuthTokensResponse
}