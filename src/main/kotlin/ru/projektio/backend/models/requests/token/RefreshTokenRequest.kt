package ru.projektio.backend.models.requests.token

data class RefreshTokenRequest(
    val refreshToken: String
)