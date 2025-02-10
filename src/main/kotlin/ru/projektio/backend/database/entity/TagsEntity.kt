package ru.projektio.backend.database.entity

import jakarta.persistence.*

@Entity
@Table(name = "`tags`")
class TagsEntity(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "hex_code", nullable = false)
    var hexCode: String,

    @OneToOne
    @JoinColumn(name = "board_id", nullable = false)
    var boardId: BoardEntity,

    ) : AbstractEntity() {
    @ManyToMany(targetEntity = TaskEntity::class)
    @JoinTable(
        name = "task_tags",
        joinColumns = [JoinColumn(name = "tag_id")],
        inverseJoinColumns = [JoinColumn(name = "task_id")]
    )
    var task: MutableList<UserEntity> = mutableListOf()
}

