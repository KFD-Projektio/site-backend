package ru.stannisl.backend.controllers

import org.springframework.web.bind.annotation.*
import ru.stannisl.backend.models.requests.user.UserRequest
import ru.stannisl.backend.service.UserService


@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService,
) : BaseApiController() {
    @GetMapping
    fun getAll() = userService.getAllUsers()
    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Long) = userService.getUserById(id)
    @PostMapping
    fun create(@RequestBody request: UserRequest) = userService.createUser(request)
}