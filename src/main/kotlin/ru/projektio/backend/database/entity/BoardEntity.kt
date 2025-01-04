package ru.projektio.backend.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "`boards`")
class BoardEntity(
    @Column(name = "title", nullable = false)
    var title: String,

    @OneToOne
    @JoinColumn(name = "owner_id", nullable = false)
    var ownerId: ru.projektio.backend.database.entity.UserEntity,

    @Column(name = "last_modified_at", nullable = false)
    var lastModifiedAt: LocalDateTime = LocalDateTime.now()
) : ru.projektio.backend.database.entity.AbstractEntity() {
    @Column(name = "description", nullable = true)
    @Lob
    var description: String? = null

    @PreUpdate
    fun updateLastModified() {
        lastModifiedAt = LocalDateTime.now()
    }

    @PrePersist
    fun persistLastModified() {
        lastModifiedAt = LocalDateTime.now()
    }
}