package ru.stannisl.backend.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.stannisl.backend.database.entity.UserEntity

//interface UserDao : CrudRepository<UserEntity, Long> {
//    fun findUserByLogin(login: String): UserEntity? = this.findAll().firstOrNull { it.login == login }
//}

interface UserDao : CrudRepository<UserEntity, Long> {
    fun findUserByLogin(login: String): UserEntity? {
        val users = this.findAll()
        println(users.forEach {
            println(it)
        })
        return users.firstOrNull { it.login == login }
    }
}