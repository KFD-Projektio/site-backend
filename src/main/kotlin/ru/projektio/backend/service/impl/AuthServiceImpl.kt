package ru.projektio.backend.service.impl

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.projektio.backend.config.properties.JwtProperties
import ru.projektio.backend.database.repository.UserDao
import ru.projektio.backend.exceptionHandler.exceptions.CredentialsMismatchException
import ru.projektio.backend.exceptionHandler.exceptions.TableEntityNotFoundException
import ru.projektio.backend.models.requests.jwtTokenRequests.RefreshTokenRequest
import ru.projektio.backend.models.requests.user.AuthUserRequest
import ru.projektio.backend.models.response.jwtTokenResponse.AuthTokensResponse
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
    private val passwordEncoder: PasswordEncoder,
    private val userDao: UserDao,
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
    override fun authUser(authRequest: AuthUserRequest): AuthTokensResponse {
        println("${authRequest.login}, ${authRequest.password}")
        val user = userDao.findUserByLogin(authRequest.login) ?: throw TableEntityNotFoundException(
            "User not found"
        )

        return if (passwordEncoder.matches(authRequest.password, user.passwordHash)) {
            AuthTokensResponse(
                accessToken = jwtTokenService.createToken(
                    user = user, expirationDate = getAccessTokenExpirationDate()
                ),
                refreshToken = "NONE" // TODO("Сделать отправку refresh tokena юзеру")
            )
        } else {
            throw CredentialsMismatchException("Password mismatch")
        }
    }

    /**
     * Обновляет токен доступа на основе предоставленного запроса на обновление.
     *
     * @param refreshTokenRequest Запрос на обновление токена доступа.
     * @return Объект AuthTokensResponse, содержащий обновленные токены аутентификации.
     * @throws NotImplementedError Если функция не реализована.
     */
    override fun refreshAccessToken(refreshTokenRequest: RefreshTokenRequest): AuthTokensResponse {
        TODO("Доделать эту штуку или свою всунуть/придумать")
    }
    /**
     * Возвращает дату истечения токена доступа.
     *
     * @return Дата истечения токена доступа.
     */
    fun getAccessTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpirationDate)
}
