package ru.stannisl.backend.service

import ru.stannisl.backend.models.requests.user.UserRequest
import ru.stannisl.backend.models.response.user.UserResponse

interface UserService {
    fun getUserById(userId: Long): UserResponse?
    fun getAllUsers(): List<UserResponse>
    fun createUser(user: UserRequest): UserResponse
    fun updateUser(id: Long, user: UserRequest): UserResponse
    fun deleteUserById(userId: Long)
}