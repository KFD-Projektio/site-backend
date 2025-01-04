package ru.stannisl.backend.config

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.stannisl.backend.config.properties.JwtProperties

@Configuration
@EnableConfigurationProperties(JwtProperties::class)
class Config