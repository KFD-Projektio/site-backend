package ru.projektio.backend.service

import ru.projektio.backend.database.entity.UserEntity
import ru.projektio.backend.models.requests.user.AuthUserRequest
import java.util.Date

/**
 * Методы для работы с токенами
 */
interface JwtTokenService {
    fun validateToken(token: String, user: UserEntity): Boolean
    fun createAccessToken(user: UserEntity): String
    fun createRefreshToken(user: UserEntity): String
    fun getRefreshTokenExpirationDate() : Date
    fun getLoginFromToken(token: String): String?

    /**
     * Возвращает дату истечения токена доступа.
     *
     * @return java.util.Date Дата истечения токена доступа.
     */
    fun getAccessTokenExpirationDate() : Date
}
