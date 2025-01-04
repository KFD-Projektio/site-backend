package ru.stannisl.backend.mappers

import org.springframework.stereotype.Component
import ru.stannisl.backend.database.entity.UserEntity
import ru.stannisl.backend.models.response.userResponse.RegisterUserResponse

@Component
class UserMapper {
    fun entityToResponse(entity: UserEntity): RegisterUserResponse {
        return RegisterUserResponse(
            id = entity.id,
            login = entity.login,
            email = entity.email,
            createdAt = entity.createdAt,
        )
    }
}