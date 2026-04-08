package com.routinebuddy.parent.repository

import com.routinebuddy.parent.data.dao.ChildDao
import com.routinebuddy.parent.data.dao.RoutineDao
import com.routinebuddy.parent.data.dao.TaskDao
import com.routinebuddy.parent.data.entity.ChildEntity
import com.routinebuddy.parent.data.entity.RoutineEntity
import com.routinebuddy.parent.data.entity.RoutineSummary
import com.routinebuddy.parent.data.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class ParentRepository(
    private val childDao: ChildDao,
    private val routineDao: RoutineDao,
    private val taskDao: TaskDao
) {
    suspend fun getChild(): ChildEntity? = childDao.getChild()

    suspend fun saveChild(name: String, ageGroup: String) {
        val now = System.currentTimeMillis()
        val existing = childDao.getChild()
        val child = if (existing == null) {
            ChildEntity(
                id = UUID.randomUUID().toString(),
                name = name,
                ageGroup = ageGroup,
                createdAt = now
            )
        } else {
            existing.copy(name = name, ageGroup = ageGroup)
        }
        childDao.insertChild(child)
    }

    fun getRoutineSummaries(childId: String): Flow<List<RoutineSummary>> = routineDao.getRoutineSummaries(childId)

    suspend fun getRoutineWithTasks(routineId: String): Pair<RoutineEntity, List<TaskEntity>>? {
        val routine = routineDao.getRoutineById(routineId) ?: return null
        val tasks = taskDao.getTasksForEdit(routineId)
        return routine to tasks
    }

    suspend fun createRoutine(
        childId: String,
        title: String,
        icon: String?,
        taskTitles: List<String>
    ) {
        val now = System.currentTimeMillis()
        val routineId = UUID.randomUUID().toString()
        val currentCount = routineDao.getRoutineCount(childId)
        val routine = RoutineEntity(
            id = routineId,
            childId = childId,
            title = title,
            icon = icon,
            orderIndex = 0,
            createdAt = now,
            updatedAt = now
        )
        routineDao.insertRoutine(routine.copy(orderIndex = currentCount))
        val tasks = taskTitles.mapIndexed { index, taskTitle ->
            TaskEntity(
                id = UUID.randomUUID().toString(),
                routineId = routineId,
                title = taskTitle,
                orderIndex = index,
                isCompleted = false,
                createdAt = now,
                updatedAt = now
            )
        }
        taskDao.insertTasks(tasks)
    }

    suspend fun updateRoutine(
        routineId: String,
        title: String,
        icon: String?,
        taskTitles: List<String>
    ) {
        val now = System.currentTimeMillis()
        val routine = routineDao.getRoutineById(routineId) ?: return
        routineDao.updateRoutine(routine.copy(title = title, icon = icon, updatedAt = now))
        taskDao.deleteTasksByRoutineId(routineId)
        val tasks = taskTitles.mapIndexed { index, taskTitle ->
            TaskEntity(
                id = UUID.randomUUID().toString(),
                routineId = routineId,
                title = taskTitle,
                orderIndex = index,
                isCompleted = false,
                createdAt = now,
                updatedAt = now
            )
        }
        taskDao.insertTasks(tasks)
    }
}
