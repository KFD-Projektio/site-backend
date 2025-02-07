package ru.projektio.backend.service.impl

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import ru.projektio.backend.config.properties.JwtProperties
import ru.projektio.backend.database.entity.UserEntity
import ru.projektio.backend.service.JwtTokenService

import java.util.*

/**
 * Сервис для работы с JWT-токенами.
 *
 * @param jwtProperties Свойства JWT, используемые для создания и проверки токенов.
 */
@Service
class JwtTokenServiceImpl(
    private val jwtProperties: JwtProperties
) : JwtTokenService {

    /**
     * Секретный ключ, используемый для подписи и проверки токенов.
     */
    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    override fun createAccessToken(user: UserEntity): String = createToken(
        user,
        getAccessTokenExpirationDate(),
        mapOf("roles" to user.roles.map { it.name })
    )

    override fun createRefreshToken(user: UserEntity): String = createToken(
        user,
        getRefreshTokenExpirationDate(),
        mapOf("roles" to user.roles.map { it.name })
    )

    /**
     * Создает JWT-токен для указанного пользователя с заданной датой истечения.
     *
     * @param user Пользователь, для которого создается токен.
     * @param expirationDate Дата истечения токена.
     * @return Строка, содержащая созданный JWT-токен.
     */
    private fun createToken(user: UserEntity, expirationDate: Date, claims: Map<String, Any> = mapOf()): String =
        Jwts.builder()
            .claims(claims)
            .subject(user.login)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .signWith(secretKey)
            .compact()

    /**
     * Проверяет валидность JWT-токена для указанного пользователя.
     *
     * @param token JWT-токен, который нужно проверить.
     * @param user Пользователь, для которого проверяется токен.
     * @return true, если токен валиден и не истек, иначе false.
     */
    override fun validateToken(token: String, user: UserEntity): Boolean {
        val login = getLoginFromToken(token)

        return login == user.login && !isTokenExpired(token)
    }

    /**
     * Проверяет, истек ли указанный JWT-токен.
     *
     * @param token JWT-токен, который нужно проверить.
     * @return true, если токен истек, иначе false.
     */
    fun isTokenExpired(token: String): Boolean = try {
        getAllClaimsFromToken(token).expiration.before(Date(System.currentTimeMillis()))
        false
    } catch (e: ExpiredJwtException) {
        true
    }

    /**
     * Возвращает логин пользователя из указанного JWT-токена.
     *
     * @param token JWT-токен, из которого нужно извлечь логин.
     * @return Логин пользователя или null, если токен недействителен.
     */
    override fun getLoginFromToken(token: String): String? = getAllClaimsFromToken(token).subject

    /**
     * Возвращает все утверждения (claims) из указанного JWT-токена.
     *
     * @param token JWT-токен, из которого нужно извлечь утверждения.
     * @return Объект Claims, содержащий утверждения токена.
     */
    private fun getAllClaimsFromToken(token: String): Claims =
        Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload

    override fun getAccessTokenExpirationDate() =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpirationDate)

    override fun getRefreshTokenExpirationDate() =
        Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpirationDate)
}
