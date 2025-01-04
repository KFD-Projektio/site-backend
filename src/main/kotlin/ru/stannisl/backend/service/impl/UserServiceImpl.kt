package ru.stannisl.backend.service.impl

import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.stannisl.backend.config.properties.JwtProperties
import ru.stannisl.backend.database.entity.UserEntity
import ru.stannisl.backend.database.repository.UserDao
import ru.stannisl.backend.mappers.UserMapper
import ru.stannisl.backend.models.requests.user.RegisterUserRequest
import ru.stannisl.backend.models.response.userResponse.RegisterUserResponse
import ru.stannisl.backend.service.JwtTokenService
import ru.stannisl.backend.service.UserService

@Service
class UserServiceImpl(
    private val userDao: UserDao,
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenService: JwtTokenService,
    private val jwtProperties: JwtProperties,
) : UserService {
    override fun getUserById(userId: Long): RegisterUserResponse? =
        userMapper.entityToResponse(userDao.findById(userId).orElseThrow { throw RuntimeException() })

    override fun getAllUsers(): List<RegisterUserResponse> =
        userDao.findAll().map {
            userMapper.entityToResponse(it)
        }

    @Transactional
    override fun createUser(user: RegisterUserRequest): RegisterUserResponse {
        val encodedPassword = passwordEncoder.encode(user.password)

        val currentUser: UserEntity = UserEntity(
            login = user.login,
            email = user.email,
            passwordHash = encodedPassword,
        )

        userDao.save(currentUser)

        return userMapper.entityToResponse(currentUser)
    }

    override fun updateUser(id: Long, user: RegisterUserRequest): RegisterUserResponse {
        TODO()
    }

    override fun deleteUserById(userId: Long) {
        TODO()
    }
}