package ru.projektio.backend.models.response.token

data class RefreshAccessTokenResponse(
    val accessToken: String,
    val refreshToken: String
)