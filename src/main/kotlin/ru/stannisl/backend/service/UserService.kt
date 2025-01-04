package ru.stannisl.backend.service

import ru.stannisl.backend.models.requests.AuthUserRequest
import ru.stannisl.backend.models.requests.RegisterUserRequest
import ru.stannisl.backend.models.response.AuthUserResponse
import ru.stannisl.backend.models.response.RegisterUserResponse

interface UserService {
    fun getUserById(userId: Long): RegisterUserResponse?
    fun getAllUsers(): List<RegisterUserResponse>
    fun createUser(user: RegisterUserRequest): RegisterUserResponse
    fun updateUser(id: Long, user: RegisterUserRequest): RegisterUserResponse
    fun deleteUserById(userId: Long)

    fun authorizeUser(authRequest: AuthUserRequest): AuthUserResponse
}