package ru.projektio.backend.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "`attachments`")
class AttachmentEntity(
    @Column(name = "file_name", nullable = false)
    var fileName: String,

    @Column(name = "file_path", nullable = false)
    var filePath: String,

    @OneToOne
    @JoinColumn(name = "task_id")
    var taskId: TaskEntity,

    @OneToOne
    @JoinColumn(name = "uploader_id")
    var uploadedId: UserEntity,
): AbstractEntity() {
    @Column(name = "uploaded_at", nullable = false)
    var uploadedAt: LocalDateTime = LocalDateTime.now()

    @PreUpdate
    fun updateLastModified() {
        uploadedAt = LocalDateTime.now()
    }

    @PrePersist
    fun persistLastModified() {
        uploadedAt = LocalDateTime.now()
    }
}






