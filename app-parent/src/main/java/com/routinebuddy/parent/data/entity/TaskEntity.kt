package com.routinebuddy.parent.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey val id: String,
    val routineId: String,
    val title: String,
    val orderIndex: Int,
    val isCompleted: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
