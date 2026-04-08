package com.routinebuddy.parent

import com.routinebuddy.parent.repository.ParentRepository

interface ParentNavigator {
    fun repository(): ParentRepository
    fun openRoutineList()
    fun openCreateRoutine()
    fun openEditRoutine(routineId: String)
    fun closeEditor()
}
