package ru.projektio.backend.models.requests.jwtTokenRequests

data class RefreshTokenRequest(
    val refreshToken: String
)