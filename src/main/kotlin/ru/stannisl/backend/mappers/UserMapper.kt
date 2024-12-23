package ru.stannisl.backend.mappers

import org.springframework.stereotype.Component
import ru.stannisl.backend.database.entity.UserEntity
import ru.stannisl.backend.models.response.UserResponse

/*
* val id: Long,
    val login: String,
    val email: String,
    val boards: List<BoardEntity>,
    val createdAt: LocalDateTime,
    * */

@Component
class UserMapper {
    fun entityToResponse(entity: UserEntity): UserResponse {
        return UserResponse(
            id = entity.id,
            login = entity.login,
            email = entity.email,
            createdAt = entity.createdAt,
            boards = entity.boards.map { board.title },
        )
    }
}