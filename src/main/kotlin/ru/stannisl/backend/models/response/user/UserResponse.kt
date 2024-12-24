package ru.stannisl.backend.models.response.user

import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val login: String,
    val email: String,
    val createdAt: LocalDateTime,
)
