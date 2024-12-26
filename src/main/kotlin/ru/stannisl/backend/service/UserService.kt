package ru.stannisl.backend.service

import ru.stannisl.backend.models.requests.RegisterUserRequest
import ru.stannisl.backend.models.response.RegisteredUserResponse

interface UserService {
    fun getUserById(userId: Long): RegisteredUserResponse?
    fun getAllUsers(): List<RegisteredUserResponse>
    fun createUser(user: RegisterUserRequest): RegisteredUserResponse
    fun updateUser(id: Long, user: RegisterUserRequest): RegisteredUserResponse
    fun deleteUserById(userId: Long)
}