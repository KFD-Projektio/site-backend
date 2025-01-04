package ru.stannisl.backend.service

import ru.stannisl.backend.database.entity.UserEntity
import java.util.Date

interface JwtTokenService {
    fun createToken(
        user: UserEntity, expirationDate: Date
    ): String

    fun validateToken(token: String, user: UserEntity): Boolean
}