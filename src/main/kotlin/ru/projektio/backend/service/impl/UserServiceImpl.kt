package ru.projektio.backend.service.impl

import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.projektio.backend.config.properties.JwtProperties
import ru.projektio.backend.database.entity.UserEntity
import ru.projektio.backend.database.repository.UserDao
import ru.projektio.backend.mappers.UserMapper
import ru.projektio.backend.models.requests.user.RegisterUserRequest
import ru.projektio.backend.models.response.userResponse.RegisterUserResponse
import ru.projektio.backend.service.JwtTokenService
import ru.projektio.backend.service.UserService

@Service
class UserServiceImpl(
    private val userDao: UserDao,
    private val userMapper: ru.projektio.backend.mappers.UserMapper,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenService: ru.projektio.backend.service.JwtTokenService,
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