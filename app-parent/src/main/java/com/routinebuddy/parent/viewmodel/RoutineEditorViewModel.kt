package com.routinebuddy.parent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routinebuddy.parent.repository.ParentRepository
import kotlinx.coroutines.launch

class RoutineEditorViewModel(private val repository: ParentRepository) : ViewModel() {
    private val _screenTitle = MutableLiveData("Create Routine")
    val screenTitle: LiveData<String> = _screenTitle

    private val _childId = MutableLiveData<String>()
    val childId: LiveData<String> = _childId

    private val _initialTitle = MutableLiveData<String>()
    val initialTitle: LiveData<String> = _initialTitle

    private val _initialIcon = MutableLiveData<String?>()
    val initialIcon: LiveData<String?> = _initialIcon

    private val _initialTasks = MutableLiveData<List<String>>(emptyList())
    val initialTasks: LiveData<List<String>> = _initialTasks

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun load(routineId: String?) {
        viewModelScope.launch {
            val child = repository.getChild() ?: return@launch
            _childId.value = child.id
            if (routineId.isNullOrBlank()) {
                _screenTitle.value = "Create Routine"
                _initialTitle.value = ""
                _initialIcon.value = "⭐"
                _initialTasks.value = emptyList()
                return@launch
            }
            val data = repository.getRoutineWithTasks(routineId) ?: return@launch
            _screenTitle.value = "Edit Routine"
            _initialTitle.value = data.first.title
            _initialIcon.value = data.first.icon
            _initialTasks.value = data.second.map { it.title }
        }
    }

    fun save(routineId: String?, title: String, icon: String?, taskTitles: List<String>, onSuccess: () -> Unit) {
        if (title.isBlank()) {
            _error.value = "Routine title is required"
            return
        }
        if (taskTitles.isEmpty()) {
            _error.value = "At least one task is required"
            return
        }
        val child = _childId.value ?: return
        viewModelScope.launch {
            if (routineId.isNullOrBlank()) {
                repository.createRoutine(child, title.trim(), icon, taskTitles)
            } else {
                repository.updateRoutine(routineId, title.trim(), icon, taskTitles)
            }
            _error.value = null
            onSuccess()
        }
    }
}
