package ru.stannisl.backend.models.response

import ru.stannisl.backend.models.response.board.BoardInfoResponse
import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val login: String,
    val email: String,
    val boards: List<BoardInfoResponse>,
    val createdAt: LocalDateTime,
)
