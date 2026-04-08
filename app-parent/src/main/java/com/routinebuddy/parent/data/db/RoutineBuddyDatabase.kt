package com.routinebuddy.parent.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.routinebuddy.parent.data.dao.ChildDao
import com.routinebuddy.parent.data.dao.RoutineDao
import com.routinebuddy.parent.data.dao.TaskDao
import com.routinebuddy.parent.data.entity.ChildEntity
import com.routinebuddy.parent.data.entity.RoutineEntity
import com.routinebuddy.parent.data.entity.TaskEntity

@Database(
    entities = [ChildEntity::class, RoutineEntity::class, TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoutineBuddyDatabase : RoomDatabase() {
    abstract fun childDao(): ChildDao
    abstract fun routineDao(): RoutineDao
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: RoutineBuddyDatabase? = null

        fun getInstance(context: Context): RoutineBuddyDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RoutineBuddyDatabase::class.java,
                    "routine_buddy.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
