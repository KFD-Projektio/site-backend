package ru.projektio.backend.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import java.util.*

/**
 * Объект, содержащий свойства для JWT токена, которые мы возьмем из конфигурационного файла application-dev.yaml.
 *
 * @property key Секретный ключ, используемый для подписи и проверки токенов.
 * @property accessTokenExpirationDate Дата истечения токена доступа.
 * @property refreshTokenExpirationDate Дата истечения токена обновления.
 */
@ConfigurationProperties("jwt")
data class JwtProperties(
    val key: String,
    val accessTokenExpirationDate: Long,
    val refreshTokenExpirationDate: Long,
)