package ru.stannisl.backend.database.repository

import org.springframework.data.repository.CrudRepository
import ru.stannisl.backend.database.entity.TaskEntity

interface TaskDao: CrudRepository<TaskEntity, Long>