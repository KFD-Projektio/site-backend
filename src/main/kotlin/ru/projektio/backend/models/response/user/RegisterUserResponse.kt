package ru.projektio.backend.models.response.user

import java.time.LocalDateTime

data class RegisterUserResponse(
    val id: Long,
    val login: String,
    val email: String,
    val createdAt: LocalDateTime
)
