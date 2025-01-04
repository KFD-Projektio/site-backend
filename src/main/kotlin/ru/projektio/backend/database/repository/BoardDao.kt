package ru.projektio.backend.database.repository

import org.springframework.data.repository.CrudRepository
import ru.projektio.backend.database.entity.BoardEntity

interface BoardDao : CrudRepository<ru.projektio.backend.database.entity.BoardEntity, Long>