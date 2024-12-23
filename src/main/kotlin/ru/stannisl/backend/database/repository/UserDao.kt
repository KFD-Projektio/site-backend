package ru.stannisl.backend.database.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.stannisl.backend.database.entity.UserEntity

@Repository
interface UserDao : CrudRepository<UserEntity, Long>