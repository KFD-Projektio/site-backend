package ru.stannisl.backend.database.repository

import org.springframework.data.repository.CrudRepository
import ru.stannisl.backend.database.entity.BoardEntity

interface BoardDao : CrudRepository<BoardEntity, Long>