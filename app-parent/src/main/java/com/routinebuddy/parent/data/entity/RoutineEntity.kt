package com.routinebuddy.parent.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "routines")
data class RoutineEntity(
    @PrimaryKey val id: String,
    val childId: String,
    val title: String,
    val icon: String?,
    val orderIndex: Int,
    val createdAt: Long,
    val updatedAt: Long
)
