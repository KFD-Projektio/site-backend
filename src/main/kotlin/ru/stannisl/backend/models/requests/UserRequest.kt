package ru.stannisl.backend.models.requests

import ru.stannisl.backend.database.entity.BoardEntity

data class UserRequest(
    val login: String,
    val email: String,
    val password: String,
//    val boards: List<BoardEntity>, // сомнительно стоит ли в последствии добавим?
)
