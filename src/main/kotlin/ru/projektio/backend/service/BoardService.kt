package ru.projektio.backend.service

import ru.projektio.backend.models.requests.board.BoardCreationRequest
import ru.projektio.backend.models.response.board.BoardResponse

interface BoardService {
    fun getAll(): List<BoardResponse>
    fun getById(id: Long): BoardResponse

    fun createBoard(boardCreationRequest: BoardCreationRequest): BoardResponse
}