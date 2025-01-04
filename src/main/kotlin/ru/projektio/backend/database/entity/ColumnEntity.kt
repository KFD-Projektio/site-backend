package ru.projektio.backend.database.entity


import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "`columns`")
class ColumnEntity(
    @Column(name = "title", nullable = false)
    var title: String,

    @OneToOne
    @JoinColumn(name = "board_id", nullable = false)
    var boardId: ru.projektio.backend.database.entity.BoardEntity,

    @Column(name = "position", nullable = false)
    var position: Int,
) : AbstractEntity() {

    @Column(name = "last_updated_at", nullable = false)
    var lastUpdatedAt: LocalDateTime = LocalDateTime.now()

    @PreUpdate
    fun updateLastModified() {
        lastUpdatedAt = LocalDateTime.now()
    }

    @PrePersist
    fun persistLastModified() {
        lastUpdatedAt = LocalDateTime.now()
    }
}