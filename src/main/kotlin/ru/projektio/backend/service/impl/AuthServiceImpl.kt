package ru.projektio.backend.service.impl

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.projektio.backend.config.properties.JwtProperties
import ru.projektio.backend.database.entity.RefreshToken
import ru.projektio.backend.database.repository.RefreshTokenDao
import ru.projektio.backend.database.repository.UserDao
import ru.projektio.backend.exceptionHandler.exceptions.CredentialsMismatchException
import ru.projektio.backend.exceptionHandler.exceptions.InvalidTokenException
import ru.projektio.backend.exceptionHandler.exceptions.TableEntityNotFoundException
import ru.projektio.backend.exceptionHandler.exceptions.TokenExpiredException
import ru.projektio.backend.models.requests.token.RefreshTokenRequest
import ru.projektio.backend.models.requests.user.AuthUserRequest
import ru.projektio.backend.models.response.token.AuthTokensResponse
import ru.projektio.backend.service.AuthService
import ru.projektio.backend.service.JwtTokenService
import java.util.*

/**
 * Реализация сервиса для аутентификации пользователей.
 *
 * @param jwtProperties Свойства JWT, используемые для создания и проверки токенов.
 * @param passwordEncoder Кодировщик паролей.
 * @param userDao DAO для работы с пользователями.
 * @param jwtTokenService Сервис для работы с JWT-токенами.
 */
@Service
class AuthServiceImpl(
    private val jwtProperties: JwtProperties,
    private val userDao: UserDao,
    private val passwordEncoder: PasswordEncoder,
    private val refreshTokenDao: RefreshTokenDao,
    private val jwtTokenService: JwtTokenService,
) : AuthService {

    /**
     * Аутентифицирует пользователя на основе предоставленных учетных данных.
     *
     * @param authRequest Запрос на аутентификацию пользователя.
     * @return Объект AuthTokensResponse, содержащий токены аутентификации.
     * @throws TableEntityNotFoundException Если пользователь не найден.
     * @throws CredentialsMismatchException Если пароль не совпадает.
     */
    @Transactional
    override fun authUser(authRequest: AuthUserRequest): AuthTokensResponse {
        val user = userDao.findUserByLogin(authRequest.login) ?: throw TableEntityNotFoundException(
            "User not found"
        )

        if (!passwordEncoder.matches(authRequest.password, user.passwordHash))
            throw CredentialsMismatchException("Password mismatch")


        refreshTokenDao.deleteByUserId(user)

        val accessToken = jwtTokenService.createAccessToken(user)
        val refreshToken = jwtTokenService.createRefreshToken(user)

        refreshTokenDao.save(
            RefreshToken(
                token = refreshToken,
                expiresAt = jwtTokenService.getRefreshTokenExpirationDate(),
                userId = user
            )
        )

        return AuthTokensResponse(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }

    /**
     * Обновляет токен доступа на основе предоставленного запроса на обновление.
     *
     * @param refreshTokenRequest Запрос на обновление токена доступа.
     * @return Объект AuthTokensResponse, содержащий обновленные токены аутентификации.
     * @throws NotImplementedError Если функция не реализована.
     */
    override fun refreshAccessToken(refreshTokenRequest: RefreshTokenRequest): AuthTokensResponse {
        val refreshToken = refreshTokenDao.findByToken(refreshTokenRequest.refreshToken)
            ?: throw InvalidTokenException("Invalid refresh token")

        if (refreshToken.expiresAt.before(Date())) {
            refreshTokenDao.delete(refreshToken)
            throw TokenExpiredException("Refresh token expired")
        }

        val user = refreshToken.userId
        refreshTokenDao.delete(refreshToken)

        val newAccessToken = jwtTokenService.createAccessToken(user)
        val newRefreshToken = jwtTokenService.createRefreshToken(user)

        refreshTokenDao.save(
            RefreshToken(
                token = newRefreshToken,
                expiresAt = jwtTokenService.getRefreshTokenExpirationDate(),
                userId = user
            )
        )

        return AuthTokensResponse(
            accessToken = newAccessToken,
            refreshToken = newRefreshToken
        )
    }
}
