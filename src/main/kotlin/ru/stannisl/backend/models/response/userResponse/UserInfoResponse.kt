package ru.stannisl.backend.models.response.userResponse

import java.time.LocalDateTime

data class UserInfoResponse(
        val id: Long,
        val login: String,
        val email: String,
        val createdAt: LocalDateTime
    )

