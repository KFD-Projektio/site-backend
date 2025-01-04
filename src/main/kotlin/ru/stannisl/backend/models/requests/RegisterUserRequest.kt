package ru.stannisl.backend.models.requests

data class RegisterUserRequest(
    val login: String,
    val email: String,
    val password: String
)
