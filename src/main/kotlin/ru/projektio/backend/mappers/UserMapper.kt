package ru.projektio.backend.mappers

import org.springframework.stereotype.Component
import ru.projektio.backend.database.entity.UserEntity
import ru.projektio.backend.models.response.user.RegisterUserResponse

/**
 * Компонент для преобразования сущностей пользователей в DTO.
 */
@Component
class UserMapper : AbstractMapper<UserEntity, RegisterUserResponse> {
    override fun entityToResponse(entity: UserEntity): RegisterUserResponse {
        return RegisterUserResponse(
            id = entity.id,
            login = entity.login,
            email = entity.email,
            createdAt = entity.createdAt
        )
    }
}

