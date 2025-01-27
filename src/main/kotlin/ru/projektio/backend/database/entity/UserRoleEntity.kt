package ru.projektio.backend.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "roles")
class UserRoleEntity(
    @Column(name = "name", nullable = false, unique = true)
    var name: String
) : AbstractEntity()
