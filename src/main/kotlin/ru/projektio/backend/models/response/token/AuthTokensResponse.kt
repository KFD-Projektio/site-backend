package ru.projektio.backend.models.response.token

data class AuthTokensResponse(
    val accessToken: String,
    val refreshToken: String
)