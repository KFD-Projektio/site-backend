package ru.projektio.backend.models.requests.user

data class RegisterUserRequest(
    val login: String,
    val email: String,
    val password: String
)
