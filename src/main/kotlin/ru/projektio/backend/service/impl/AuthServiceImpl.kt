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

@Service
class AuthServiceImpl(
    private val jwtProperties: JwtProperties,
    private val passwordEncoder: PasswordEncoder,
    private val userDao: UserDao,
    private val jwtTokenService: ru.projektio.backend.service.JwtTokenService,
) : ru.projektio.backend.service.AuthService {
    override fun authUser(authRequest: ru.projektio.backend.models.requests.user.AuthUserRequest): AuthTokensResponse {
        println("${authRequest.login}, ${authRequest.password}")
        val user = userDao.findUserByLogin(authRequest.login) ?: throw ru.projektio.backend.exceptionHandler.exceptions.TableEntityNotFoundException(
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
            throw ru.projektio.backend.exceptionHandler.exceptions.CredentialsMismatchException("Password mismatch")
        }
    }

    override fun refreshAccessToken(refreshTokenRequest: RefreshTokenRequest): AuthTokensResponse {
        TODO("Доделать эту штуку или свою всунуть/придумать")
    }

    fun getAccessTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpirationDate)
}