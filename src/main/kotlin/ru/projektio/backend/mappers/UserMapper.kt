package ru.projektio.backend.mappers

import org.springframework.stereotype.Component
import ru.projektio.backend.database.entity.UserEntity
import ru.projektio.backend.models.response.userResponse.RegisterUserResponse

/**
 * Компонент для преобразования сущностей пользователей в DTO.
 */
@Component
class UserMapper {
    /**
     * Преобразует сущность пользователя в объект ответа RegisterUserResponse.
     *
     * @param entity Сущность пользователя, которую нужно преобразовать.
     * @return Объект RegisterUserResponse, содержащий информацию о пользователе.
     */
    fun entityToUserRegisterResponse(entity: UserEntity): RegisterUserResponse {
        return RegisterUserResponse(
            id = entity.id,
            login = entity.login,
            email = entity.email,
            createdAt = entity.createdAt,
        )
    }
}

