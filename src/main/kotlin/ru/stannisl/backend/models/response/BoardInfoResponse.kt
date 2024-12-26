package ru.stannisl.backend.models.response

import java.time.LocalDateTime

data class BoardInfoResponse(
    val id: Long,
    val title: String,
    val ownerId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)