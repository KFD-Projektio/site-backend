package ru.stannisl.backend.controllers.v1

import org.springframework.web.bind.annotation.*
import ru.stannisl.backend.models.requests.RegisterUserRequest
import ru.stannisl.backend.service.UserService


@RestController
@RequestMapping("/v1/users")
class UserController(
    val userService: UserService,
) {
    @GetMapping
    fun getAll() = userService.getAllUsers()
    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Long) = userService.getUserById(id)
    @PostMapping
    fun create(@RequestBody request: RegisterUserRequest) = userService.createUser(request)
}