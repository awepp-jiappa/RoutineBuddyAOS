package com.routinebuddy.parent.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.routinebuddy.parent.data.entity.RoutineEntity
import com.routinebuddy.parent.data.entity.RoutineSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutineDao {
    @Query("""
        SELECT r.id, r.title, r.icon, COUNT(t.id) AS taskCount
        FROM routines r
        LEFT JOIN tasks t ON t.routineId = r.id
        WHERE r.childId = :childId
        GROUP BY r.id
        ORDER BY r.orderIndex ASC, r.createdAt ASC
    """)
    fun getRoutineSummaries(childId: String): Flow<List<RoutineSummary>>

    @Query("SELECT * FROM routines WHERE id = :routineId LIMIT 1")
    suspend fun getRoutineById(routineId: String): RoutineEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoutine(routine: RoutineEntity)

    @Query("SELECT COUNT(*) FROM routines WHERE childId = :childId")
    suspend fun getRoutineCount(childId: String): Int

    @Update
    suspend fun updateRoutine(routine: RoutineEntity)

    @Query("DELETE FROM routines WHERE id = :routineId")
    suspend fun deleteRoutine(routineId: String)
}
