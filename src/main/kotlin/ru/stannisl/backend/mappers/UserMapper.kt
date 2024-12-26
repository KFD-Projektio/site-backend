package ru.stannisl.backend.mappers

import org.springframework.stereotype.Component
import ru.stannisl.backend.database.entity.UserEntity
import ru.stannisl.backend.models.response.RegisteredUserResponse

@Component
class UserMapper {
    fun entityToResponse(entity: UserEntity): RegisteredUserResponse {
        return RegisteredUserResponse(
            id = entity.id,
            login = entity.login,
            email = entity.email,
            createdAt = entity.createdAt,
        )
    }
}