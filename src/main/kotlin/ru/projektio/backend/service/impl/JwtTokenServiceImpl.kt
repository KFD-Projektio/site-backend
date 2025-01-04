package ru.projektio.backend.service.impl

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import ru.projektio.backend.config.properties.JwtProperties
import ru.projektio.backend.database.entity.UserEntity
import ru.projektio.backend.service.JwtTokenService
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@Service
class JwtTokenServiceImpl(
    private val jwtProperties: JwtProperties
) : ru.projektio.backend.service.JwtTokenService {

    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    override fun createToken(user: UserEntity, expirationDate: Date): String =
        Jwts.builder().claims().subject(user.login).issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate).and().signWith(secretKey).compact()

    override fun validateToken(token: String, user: UserEntity): Boolean {
        val login = getLoginFromToken(token)

        return login == user.login && !isTokenExpired(token)
    }

    fun isTokenExpired(token: String): Boolean =
        getAllClaimsFromToken(token).expiration.before(Date(System.currentTimeMillis()))

    fun getLoginFromToken(token: String): String? = getAllClaimsFromToken(token).subject

    private fun getAllClaimsFromToken(token: String): Claims =
        Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).payload

}