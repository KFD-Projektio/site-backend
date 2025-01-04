package ru.projektio.backend.models.response.jwtTokenResponse

data class RefreshAccessTokenResponse(
    val accessToken: String,
    val refreshToken: String
)