package ru.stannisl.backend.service

import ru.stannisl.backend.models.requests.jwtTokenRequests.RefreshTokenRequest
import ru.stannisl.backend.models.requests.user.AuthUserRequest
import ru.stannisl.backend.models.response.jwtTokenResponse.AuthTokensResponse

interface AuthService {
    fun authUser(authRequest: AuthUserRequest): AuthTokensResponse
    fun refreshAccessToken(refreshTokenRequest: RefreshTokenRequest): AuthTokensResponse
}