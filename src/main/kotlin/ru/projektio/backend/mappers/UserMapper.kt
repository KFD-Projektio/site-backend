package ru.projektio.backend.mappers

import org.springframework.stereotype.Component
import ru.projektio.backend.database.entity.UserEntity
import ru.projektio.backend.models.response.userResponse.RegisterUserResponse

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