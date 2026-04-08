package com.routinebuddy.parent.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "children")
data class ChildEntity(
    @PrimaryKey val id: String,
    val name: String,
    val ageGroup: String,
    val createdAt: Long
)
