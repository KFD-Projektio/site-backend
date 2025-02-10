package ru.projektio.backend.mappers

import org.springframework.stereotype.Component
import ru.projektio.backend.database.entity.BoardEntity
import ru.projektio.backend.models.response.board.BoardResponse

@Component
class BoardMapper : AbstractMapper<BoardEntity, BoardResponse> {
    override fun entityToResponse(entity: BoardEntity): BoardResponse {
        return BoardResponse(
            id = entity.id,
            title = entity.title,
            ownerId = entity.ownerId.id,
            createdAt = entity.createdAt,
            updatedAt = entity.lastModifiedAt
        )
    }
}