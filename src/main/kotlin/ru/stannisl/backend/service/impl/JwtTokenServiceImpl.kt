package ru.stannisl.backend.service.impl

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import ru.stannisl.backend.config.properties.JwtProperties
import ru.stannisl.backend.database.entity.UserEntity
import ru.stannisl.backend.service.JwtTokenService
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

@Service
class JwtTokenServiceImpl(
    private val jwtProperties: JwtProperties
) : JwtTokenService {

    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    override fun createToken(user: UserEntity, expirationDate: Date): String =
        Jwts.builder().claims().subject(user.login).issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate).and().signWith(secretKey).compact()

    override fun validateToken(token: String): Boolean {
        TODO("Not yet implemented")
    }
}