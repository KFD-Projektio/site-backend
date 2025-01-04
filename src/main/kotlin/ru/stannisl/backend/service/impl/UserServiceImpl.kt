package ru.stannisl.backend.service.impl

import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.stannisl.backend.config.properties.JwtProperties
import ru.stannisl.backend.database.entity.UserEntity
import ru.stannisl.backend.database.repository.UserDao
import ru.stannisl.backend.exceptionHandler.exceptions.CredentialsMismatchException
import ru.stannisl.backend.exceptionHandler.exceptions.TableEntityNotFoundException
import ru.stannisl.backend.mappers.UserMapper
import ru.stannisl.backend.models.requests.AuthUserRequest
import ru.stannisl.backend.models.requests.RegisterUserRequest
import ru.stannisl.backend.models.response.AuthUserResponse
import ru.stannisl.backend.models.response.RegisterUserResponse
import ru.stannisl.backend.service.JwtTokenService
import ru.stannisl.backend.service.UserService
import java.util.*

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

    override fun authorizeUser(authRequest: AuthUserRequest): AuthUserResponse {
        println("${authRequest.login}, ${authRequest.password}")
        val user = userDao.findUserByLogin(authRequest.login) ?: throw TableEntityNotFoundException("User not found")
        // Добавить НОРМ проверку на несущ юзера (щас лень)

        return if (passwordEncoder.matches(authRequest.password, user.passwordHash)) {
            AuthUserResponse(
                token = jwtTokenService.createToken(
                    user = user,
                    expirationDate = getAccessTokenExpirationDate()
                )
            )
        } else {
            throw CredentialsMismatchException("Password mismatch")
        }
    }

    fun getAccessTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpirationDate)
}