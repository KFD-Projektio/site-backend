package ru.projektio.backend.models.response.board

import java.time.LocalDateTime

data class BoardResponse(
    val id: Long,
    val title: String,
    val ownerId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)