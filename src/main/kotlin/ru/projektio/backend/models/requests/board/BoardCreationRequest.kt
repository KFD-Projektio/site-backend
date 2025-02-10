package ru.projektio.backend.models.requests.board

data class BoardCreationRequest(
    val title: String,
    val description: String?,
)