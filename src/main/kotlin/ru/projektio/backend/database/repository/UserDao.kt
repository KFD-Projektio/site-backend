package ru.projektio.backend.database.repository

import org.springframework.data.repository.CrudRepository
import ru.projektio.backend.database.entity.UserEntity

/**
 * Методы для работы с таблицей Базы данных users
 */
interface UserDao : CrudRepository<UserEntity, Long> {
    fun findUserByLogin(login: String): UserEntity? = this.findAll().firstOrNull { it.login == login }
    fun findUserByEmail(email: String): UserEntity? = this.findAll().firstOrNull { it.email == email }
}