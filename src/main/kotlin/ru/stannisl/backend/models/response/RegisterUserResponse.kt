package ru.stannisl.backend.models.response

import java.time.LocalDateTime

data class RegisterUserResponse(
    val id: Long,
    val login: String,
    val email: String,
    val createdAt: LocalDateTime
)
