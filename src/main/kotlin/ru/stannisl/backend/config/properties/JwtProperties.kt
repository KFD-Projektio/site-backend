package ru.stannisl.backend.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

@ConfigurationProperties("jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpirationDate: Long,
    val refreshTokenExpirationDate: Long,
)