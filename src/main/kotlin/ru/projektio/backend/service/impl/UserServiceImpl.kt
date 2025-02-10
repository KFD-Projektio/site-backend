package ru.projektio.backend.service.impl

import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.projektio.backend.config.properties.JwtProperties
import ru.projektio.backend.database.entity.UserEntity
import ru.projektio.backend.database.repository.UserDao
import ru.projektio.backend.exceptionHandler.exceptions.NotUniqueRegisterDataException
import ru.projektio.backend.mappers.UserMapper
import ru.projektio.backend.models.requests.user.RegisterUserRequest
import ru.projektio.backend.models.response.user.RegisterUserResponse
import ru.projektio.backend.service.JwtTokenService
import ru.projektio.backend.service.UserService

/**
 * Реализация сервиса для работы с пользователями.
 *
 * @param userDao DAO для работы с пользователями.
 * @param userMapper Маппер для преобразования сущностей пользователей.
 * @param passwordEncoder Кодировщик паролей.
 * @param jwtTokenService Сервис для работы с JWT-токенами.
 * @param jwtProperties Свойства JWT.
 */
@Service
class UserServiceImpl(
    private val userDao: UserDao,
    private val userMapper: UserMapper,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenService: JwtTokenService,
    private val jwtProperties: JwtProperties,
) : UserService {

    /**
     * Получает пользователя по его идентификатору.
     *
     * @param userId Идентификатор пользователя.
     * @return Объект RegisterUserResponse, содержащий информацию о пользователе, или null, если пользователь не найден.
     */
    override fun getUserById(userId: Long): RegisterUserResponse? =
        userMapper.entityToResponse(userDao.findById(userId).orElseThrow { throw RuntimeException() })

    /**
     * Получает список всех пользователей.
     *
     * @return Список объектов RegisterUserResponse, содержащих информацию о пользователях.
     */
    override fun getAllUsers(): List<RegisterUserResponse> =
        userDao.findAll().map {
            userMapper.entityToResponse(it)
        }

    /**
     * Создает нового пользователя.
     *
     * @param user RegisterUserRequest Запрос на регистрацию пользователя.
     * @return Объект RegisterUserResponse, содержащий информацию о созданном пользователе.
     */
    @Transactional
    override fun createUser(user: RegisterUserRequest): RegisterUserResponse {

        if (userDao.findUserByLogin(user.login) != null) {
            throw NotUniqueRegisterDataException("Login already exists")
        }
        if (userDao.findUserByEmail(user.email) != null) {
            throw NotUniqueRegisterDataException("Email already exists")
        }

        val encodedPassword = passwordEncoder.encode(user.password)
        val currentUser = UserEntity(
            login = user.login,
            email = user.email,
            passwordHash = encodedPassword,
        )

        userDao.save(currentUser)

        return userMapper.entityToResponse(currentUser)
    }

    /**
     * Обновляет информацию о пользователе.
     *
     * @param id Идентификатор пользователя.
     * @param user Запрос на обновление пользователя.
     * @return Объект RegisterUserResponse, содержащий информацию об обновленном пользователе.
     */
    override fun updateUser(id: Long, user: RegisterUserRequest): RegisterUserResponse {
        TODO()
    }

    /**
     * Удаляет пользователя по его идентификатору.
     *
     * @param userId Идентификатор пользователя.
     */
    override fun deleteUserById(userId: Long) {
        TODO()
    }
}