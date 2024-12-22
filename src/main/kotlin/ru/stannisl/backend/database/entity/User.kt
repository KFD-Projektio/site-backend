package ru.stannisl.backend.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "`users`")
class User(
    @Column(name = "password_hash", nullable = false)
    var passwordHash: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "login", nullable = false)
    var login: String,
) : AbstractEntity() {
    @ManyToMany(targetEntity = Board::class)
    @JoinTable(
        name = "user_boards",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "board_id")]
    )
    var boards: MutableList<Board> = mutableListOf()
}