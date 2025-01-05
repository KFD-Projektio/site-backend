package ru.projektio.backend.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.projektio.backend.config.properties.JwtProperties

/**
 * Конфигурационный класс для подключения свойств JWT.
 *
 * @property JwtProperties Свойства JWT, используемые для создания и проверки токенов.
 */
@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Config

