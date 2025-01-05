package ru.projektio.backend.controllers.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.projektio.backend.models.requests.user.RegisterUserRequest
import ru.projektio.backend.models.response.userResponse.RegisterUserResponse
import ru.projektio.backend.service.UserService


/**
 * Контроллер для обработки запросов, связанных с пользователями.
 *
 * @param userService Сервис для работы с пользователями.
 */
@RestController
@RequestMapping("/v1/users")
class UserController(
    val userService: UserService,
) {
    /**
     * Получает список всех пользователей.
     *
     * @return ResponseEntity с кодом состояния OK и телом, содержащим список объектов RegisterUserResponse.
     */
    @GetMapping
    fun getAll(): ResponseEntity<List<RegisterUserResponse>> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getAllUsers())

    /**
     * Получает пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return ResponseEntity с кодом состояния OK и телом, содержащим объект RegisterUserResponse.
     */
    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Long): ResponseEntity<RegisterUserResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getUserById(id))

    /**
     * Создает нового пользователя.
     *
     * @param request Запрос на регистрацию пользователя.
     * @return ResponseEntity с кодом состояния OK и телом, содержащим объект RegisterUserResponse.
     */
    @PostMapping
    fun create(@RequestBody request: RegisterUserRequest): ResponseEntity<RegisterUserResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.createUser(request))
}

