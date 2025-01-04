package ru.projektio.backend.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.projektio.backend.config.properties.JwtProperties

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Config