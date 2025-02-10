package ru.projektio.backend.service

import ru.projektio.backend.database.entity.UserEntity
import java.util.*

/**
 * Методы для работы с токенами
 */
interface JwtTokenService {
    fun validateToken(token: String, user: UserEntity): Boolean
    fun createAccessToken(user: UserEntity): String
    fun createRefreshToken(user: UserEntity): String
    fun getRefreshTokenExpirationDate(): Date
    fun getLoginFromToken(token: String): String?
    fun getAccessTokenExpirationDate(): Date
}
