package ru.stannisl.backend.service.impl

import ru.stannisl.backend.database.repository.UserDao
import ru.stannisl.backend.models.requests.UserRequest
import ru.stannisl.backend.models.response.UserResponse
import ru.stannisl.backend.service.UserService

class UserServiceImpl(
    val userDao: UserDao,
) : UserService {
    override fun getUserById(userId: Long): UserResponse? { TODO() }
    override fun getAllUsers(): List<UserResponse> { TODO() }
    override fun createUser(user: UserRequest): UserResponse { TODO() }
    override fun updateUser(id: Long, user: UserRequest): UserResponse { TODO() }
    override fun deleteUserById(userId: Long) { TODO() }
}