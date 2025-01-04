package ru.projektio.backend.database.repository

import org.springframework.data.repository.CrudRepository
import ru.projektio.backend.database.entity.TaskEntity

interface TaskDao: CrudRepository<TaskEntity, Long>