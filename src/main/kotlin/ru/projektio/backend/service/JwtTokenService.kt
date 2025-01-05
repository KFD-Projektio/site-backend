package ru.projektio.backend.service

import ru.projektio.backend.database.entity.UserEntity
import java.util.Date

/**
 * Методы для работы с токенами
 */
interface JwtTokenService {
    fun createToken(
        user: UserEntity, expirationDate: Date
    ): String

    fun validateToken(token: String, user: UserEntity): Boolean
}