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

    @ManyToMany(fetch = FetchType.EAGER) // Роли загружаются сразу с пользователем
    @JoinTable(
        name = "user_roles", // Название таблицы для связи
        joinColumns = [JoinColumn(name = "user_id")], // Столбец для связи с UserEntity
        inverseJoinColumns = [JoinColumn(name = "role_id")] // Столбец для связи с RoleEntity
    )
    var roles: MutableSet<UserRoleEntity> = mutableSetOf() // Используем Set, чтобы избежать дублика

}