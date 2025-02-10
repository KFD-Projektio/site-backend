package ru.projektio.backend.service.impl

import org.springframework.security.access.AuthorizationServiceException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.projektio.backend.database.entity.UserEntity
import ru.projektio.backend.exceptionHandler.exceptions.UnauthorizedException
import ru.projektio.backend.service.SecurityService

@Service
class SecurityServiceImpl : SecurityService {
    override fun getAuthenticatedUserFromContext(): UserEntity {
        val authentication = SecurityContextHolder.getContext().authentication
            ?: throw UnauthorizedException("Authentication required")

        return when (val principal = authentication.principal) {
            is UserEntity -> principal
            else -> throw AuthorizationServiceException("Invalid principal type: ${principal?.javaClass?.simpleName}")
        }
    }
}