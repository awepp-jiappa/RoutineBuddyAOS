package com.routinebuddy.parent.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.routinebuddy.parent.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE routineId = :routineId ORDER BY orderIndex ASC, createdAt ASC")
    fun getTasksByRoutineId(routineId: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE routineId = :routineId ORDER BY orderIndex ASC, createdAt ASC")
    suspend fun getTasksForEdit(routineId: String): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Query("UPDATE tasks SET isCompleted = :isCompleted, updatedAt = :updatedAt WHERE id = :taskId")
    suspend fun updateTaskChecked(taskId: String, isCompleted: Boolean, updatedAt: Long)

    @Query("DELETE FROM tasks WHERE routineId = :routineId")
    suspend fun deleteTasksByRoutineId(routineId: String)
}
