package ru.stannisl.backend.database.repository

import org.springframework.data.repository.CrudRepository
import ru.stannisl.backend.database.entity.Board

interface BoardDao : CrudRepository<Board, Long>