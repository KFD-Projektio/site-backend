package ru.projektio.backend.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "`boards`")
class BoardEntity(
    @Column(name = "title", nullable = false)
    var title: String,

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    var ownerId: UserEntity,
) : AbstractEntity() {


    @Column(name = "last_modified_at", nullable = false)
    var lastModifiedAt: LocalDateTime = LocalDateTime.now()

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