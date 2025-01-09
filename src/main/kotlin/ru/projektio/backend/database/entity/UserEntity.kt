package ru.projektio.backend.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "`users`")
class UserEntity(
    @Column(name = "password_hash", nullable = false)
    var passwordHash: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "login", nullable = false)
    var login: String,

) : AbstractEntity() {
    @ManyToMany(targetEntity = BoardEntity::class)
    @JoinTable(
        name = "user_boards",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "board_id")]
    )
    var boards: MutableList<BoardEntity> = mutableListOf()

}