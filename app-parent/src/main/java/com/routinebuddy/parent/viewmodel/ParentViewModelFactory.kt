package com.routinebuddy.parent.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.routinebuddy.parent.repository.ParentRepository

class ParentViewModelFactory(private val repository: ParentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ChildProfileViewModel::class.java) -> ChildProfileViewModel(repository) as T
            modelClass.isAssignableFrom(RoutineListViewModel::class.java) -> RoutineListViewModel(repository) as T
            modelClass.isAssignableFrom(RoutineEditorViewModel::class.java) -> RoutineEditorViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
