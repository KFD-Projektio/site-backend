package ru.projektio.backend.database.repository

import org.springframework.data.repository.CrudRepository
import ru.projektio.backend.database.entity.RefreshToken
import ru.projektio.backend.database.entity.UserEntity

interface RefreshTokenDao : CrudRepository<RefreshToken, Long> {
    fun findByToken(token: String): RefreshToken?
    fun deleteByUserId(user: UserEntity)
}