package ru.stannisl.backend.service.impl

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import ru.stannisl.backend.config.properties.JwtProperties
import ru.stannisl.backend.database.repository.UserDao
import ru.stannisl.backend.exceptionHandler.exceptions.CredentialsMismatchException
import ru.stannisl.backend.exceptionHandler.exceptions.TableEntityNotFoundException
import ru.stannisl.backend.models.requests.jwtTokenRequests.RefreshTokenRequest
import ru.stannisl.backend.models.requests.user.AuthUserRequest
import ru.stannisl.backend.models.response.jwtTokenResponse.AuthTokensResponse
import ru.stannisl.backend.service.AuthService
import ru.stannisl.backend.service.JwtTokenService
import java.util.*

@Service
class AuthServiceImpl(
    private val jwtProperties: JwtProperties,
    private val passwordEncoder: PasswordEncoder,
    private val userDao: UserDao,
    private val jwtTokenService: JwtTokenService,
) : AuthService {
    override fun authUser(authRequest: AuthUserRequest): AuthTokensResponse {
        println("${authRequest.login}, ${authRequest.password}")
        val user = userDao.findUserByLogin(authRequest.login) ?: throw TableEntityNotFoundException("User not found")

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

    override fun refreshAccessToken(refreshTokenRequest: RefreshTokenRequest): AuthTokensResponse {
        TODO("Доделать эту штуку или свою всунуть/придумать")
    }

    fun getAccessTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpirationDate)
}