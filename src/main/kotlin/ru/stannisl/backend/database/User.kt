package ru.stannisl.backend.database

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "`user`")
class User(
    @Column(name = "password_hash", nullable = false)
    var passwordHash: String,

    @Column(name = "email", nullable = false)
    var email: String,

    @Column(name = "login", nullable = false)
    var login: String,
) : AbstractEntity() {}