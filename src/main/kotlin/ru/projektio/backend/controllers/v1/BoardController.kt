package ru.projektio.backend.controllers.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.projektio.backend.models.requests.board.BoardCreationRequest
import ru.projektio.backend.models.response.board.BoardResponse
import ru.projektio.backend.service.BoardService

@RestController
@RequestMapping("/v1/board")
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<BoardResponse>> = ResponseEntity
        .status(HttpStatus.OK)
        .body(boardService.getAll())

    @GetMapping("/{id}")
    fun getBoard(@PathVariable id: Long): ResponseEntity<BoardResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(boardService.getById(id))

    @PostMapping
    fun createBoard(@RequestBody request: BoardCreationRequest): ResponseEntity<BoardResponse> =
        ResponseEntity
            .status(HttpStatus.CREATED)
            .body(boardService.createBoard(request))
}