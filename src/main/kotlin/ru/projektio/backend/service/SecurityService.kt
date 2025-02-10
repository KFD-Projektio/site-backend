package ru.projektio.backend.service

import ru.projektio.backend.database.entity.UserEntity

interface SecurityService {
    fun getAuthenticatedUserFromContext(): UserEntity
}