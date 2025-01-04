package ru.stannisl.backend.models.response.jwtTokenResponse

data class AuthTokensResponse(
    val accessToken: String,
    val refreshToken: String
)