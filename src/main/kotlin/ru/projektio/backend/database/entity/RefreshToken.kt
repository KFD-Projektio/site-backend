package ru.projektio.backend.database.entity

import jakarta.persistence.*
import java.util.Date

@Entity
class RefreshToken(
    @Column(unique = true, nullable = false)
    val token: String,

    @Column(nullable = false)
    val expiresAt: Date,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id" ,nullable = false)
    val userId: UserEntity
) : AbstractEntity()
