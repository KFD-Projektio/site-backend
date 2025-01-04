package ru.stannisl.backend.service

import ru.stannisl.backend.models.requests.user.RegisterUserRequest
import ru.stannisl.backend.models.response.userResponse.RegisterUserResponse

interface UserService {
    fun getUserById(userId: Long): RegisterUserResponse?
    fun getAllUsers(): List<RegisterUserResponse>
    fun createUser(user: RegisterUserRequest): RegisterUserResponse
    fun updateUser(id: Long, user: RegisterUserRequest): RegisterUserResponse
    fun deleteUserById(userId: Long)

}