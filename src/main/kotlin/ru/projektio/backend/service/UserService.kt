package ru.projektio.backend.service

import ru.projektio.backend.models.requests.user.RegisterUserRequest
import ru.projektio.backend.models.response.userResponse.RegisterUserResponse


/**
 * Методы для обработки запросов пользователя на эндпоинт users
 */
interface UserService {
    fun getUserById(userId: Long): RegisterUserResponse?
    fun getAllUsers(): List<RegisterUserResponse>
    fun     createUser(user: RegisterUserRequest): RegisterUserResponse
    fun updateUser(id: Long, user: RegisterUserRequest): RegisterUserResponse
    fun deleteUserById(userId: Long)
}