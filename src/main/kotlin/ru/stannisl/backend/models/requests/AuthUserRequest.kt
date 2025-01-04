package ru.stannisl.backend.models.requests

data class AuthUserRequest(
    val login: String,
    val password: String
)
