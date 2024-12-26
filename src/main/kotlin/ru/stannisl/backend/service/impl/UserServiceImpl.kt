package ru.stannisl.backend.service.impl

import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.stannisl.backend.database.entity.UserEntity
import ru.stannisl.backend.database.repository.UserDao
import ru.stannisl.backend.mappers.UserMapper
import ru.stannisl.backend.models.requests.RegisterUserRequest
import ru.stannisl.backend.models.response.RegisteredUserResponse
import ru.stannisl.backend.service.UserService

@Service
class UserServiceImpl(
    val userDao: UserDao,
    val userMapper: UserMapper,
    val passwordEncoder: PasswordEncoder,
) : UserService {
    override fun getUserById(userId: Long): RegisteredUserResponse? =
        userMapper.entityToResponse(userDao.findById(userId).orElseThrow{ throw RuntimeException() })

    override fun getAllUsers(): List<RegisteredUserResponse> =
        userDao.findAll().map {
            userMapper.entityToResponse(it)
        }

    @Transactional
    override fun createUser(user: RegisterUserRequest): RegisteredUserResponse {
        val encodedPassword = passwordEncoder.encode(user.password)

        val currentUser: UserEntity = UserEntity(
            login = user.login,
            email = user.email,
            passwordHash = encodedPassword,
        )

        userDao.save(currentUser)

        return userMapper.entityToResponse(currentUser)
    }

    override fun updateUser(id: Long, user: RegisterUserRequest): RegisteredUserResponse { TODO() }

    override fun deleteUserById(userId: Long) { TODO() }
}