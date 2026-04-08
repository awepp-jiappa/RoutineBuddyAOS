package com.routinebuddy.parent.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.routinebuddy.parent.data.entity.ChildEntity

@Dao
interface ChildDao {
    @Query("SELECT * FROM children LIMIT 1")
    suspend fun getChild(): ChildEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChild(child: ChildEntity)

    @Update
    suspend fun updateChild(child: ChildEntity)
}
