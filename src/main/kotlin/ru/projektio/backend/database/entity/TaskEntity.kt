package ru.projektio.backend.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "`tasks`")
class TaskEntity(

    @Column(name = "title")
    var title: String,

    @Column(name = "position", nullable = true)
    var position: Int,

    @OneToOne
    @JoinColumn(name = "creator_id")
    var creatorId: UserEntity,

    @OneToOne
    @JoinColumn(name = "column_id")
    var column: ColumnEntity,
) : AbstractEntity() {

    @Column(name = "deadline", nullable = true)
    var deadline: LocalDateTime? = null

    @Column(name = "priority", nullable = false)
    var priority: Int = 5

    @Lob
    @Column(name = "description", nullable = false)
    var description: String? = null

    @Column(name = "last_updated_at", nullable = false)
    var lastUpdatedAt: LocalDateTime = LocalDateTime.now()

    @ManyToMany(targetEntity = UserEntity::class)
    @JoinTable(
        name = "user_tasks",
        joinColumns = [JoinColumn(name = "task_id")],
        inverseJoinColumns = [JoinColumn(name = "assignee_id")]
        )
    var assignees: MutableList<UserEntity> = mutableListOf()

    @PreUpdate
    fun updateLastModified() {
        lastUpdatedAt = LocalDateTime.now()
    }

    @PrePersist
    fun persistLastModified() {
        lastUpdatedAt = LocalDateTime.now()
    }
}