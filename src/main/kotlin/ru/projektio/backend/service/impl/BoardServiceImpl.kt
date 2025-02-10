package ru.projektio.backend.service.impl

import org.springframework.stereotype.Service
import ru.projektio.backend.database.entity.BoardEntity
import ru.projektio.backend.database.repository.BoardDao
import ru.projektio.backend.exceptionHandler.exceptions.EntityNotFoundException
import ru.projektio.backend.mappers.BoardMapper
import ru.projektio.backend.models.requests.board.BoardCreationRequest
import ru.projektio.backend.models.response.board.BoardResponse
import ru.projektio.backend.service.BoardService
import ru.projektio.backend.service.SecurityService

@Service
class BoardServiceImpl(
    private val boardDao: BoardDao,
    private val boardMapper: BoardMapper,
    private val securityService: SecurityService,
) : BoardService {
    override fun getAll(): List<BoardResponse> = boardDao.findAll().map { boardMapper.entityToResponse(it) }

    override fun getById(id: Long): BoardResponse = boardMapper.entityToResponse(boardDao.findById(id).orElseThrow {
        throw EntityNotFoundException("Board Entity with id $id not found")
    })

    override fun createBoard(boardCreationRequest: BoardCreationRequest): BoardResponse {

        val boardEntity = BoardEntity(boardCreationRequest.title, securityService.getAuthenticatedUserFromContext()).apply { description = boardCreationRequest.description }
        boardDao.save(boardEntity)

        return boardMapper.entityToResponse(boardEntity)
    }
}