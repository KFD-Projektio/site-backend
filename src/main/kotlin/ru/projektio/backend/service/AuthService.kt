package ru.projektio.backend.service

import ru.projektio.backend.models.requests.jwtTokenRequests.RefreshTokenRequest
import ru.projektio.backend.models.requests.user.AuthUserRequest
import ru.projektio.backend.models.response.jwtTokenResponse.AuthTokensResponse

interface AuthService {
    fun authUser(authRequest: ru.projektio.backend.models.requests.user.AuthUserRequest): AuthTokensResponse
    fun refreshAccessToken(refreshTokenRequest: RefreshTokenRequest): AuthTokensResponse
}