package ru.stannisl.backend.controllers.v1

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.stannisl.backend.models.requests.user.RegisterUserRequest
import ru.stannisl.backend.models.response.userResponse.RegisterUserResponse
import ru.stannisl.backend.service.UserService


@RestController
@RequestMapping("/v1/users")
class UserController(
    val userService: UserService,
) {
    @GetMapping
    fun getAll(): ResponseEntity<List<RegisterUserResponse>> =
        ResponseEntity
        .status(HttpStatus.OK)
        .body(userService.getAllUsers())

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Long): ResponseEntity<RegisterUserResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getUserById(id))

    @PostMapping
    fun create(@RequestBody request: RegisterUserRequest): ResponseEntity<RegisterUserResponse> =
        ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.createUser(request))
}