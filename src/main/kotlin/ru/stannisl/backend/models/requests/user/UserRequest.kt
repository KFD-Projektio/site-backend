package ru.stannisl.backend.models.requests.user

data class UserRequest(
    val login: String,
    val email: String,
    val password: String,
)
