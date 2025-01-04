package ru.stannisl.backend.models.requests.user

data class AuthUserRequest(
    val login: String,
    val password: String
)
