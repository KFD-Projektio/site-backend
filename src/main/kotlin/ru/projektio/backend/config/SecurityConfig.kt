package ru.projektio.backend.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import ru.projektio.backend.config.properties.JwtProperties

/**
 * Конфигурация безопасности приложения.
 */
@Configuration
@EnableWebSecurity
class SecurityConfig {
    /**
     * Создает экземпляр PasswordEncoder для кодирования паролей.
     *
     * @return Экземпляр PasswordEncoder.
     */
    @Bean
    fun getEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    /**
     * Создает цепочку фильтров безопасности.
     *
     * @param http Объект HttpSecurity для настройки безопасности.
     * @return Цепочка фильтров безопасности.
     */
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.anyRequest().permitAll() // Разрешает доступ ко всем остальным URL-адресам без аутентификации
            }
            .build() // Строит и возвращает цепочку фильтров безопасности
    }
}
