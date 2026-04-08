package com.routinebuddy.parent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.routinebuddy.parent.data.entity.RoutineSummary
import com.routinebuddy.parent.repository.ParentRepository
import kotlinx.coroutines.launch

class RoutineListViewModel(private val repository: ParentRepository) : ViewModel() {
    private val _childId = MutableLiveData<String>()
    val routines: LiveData<List<RoutineSummary>> = _childId.switchMap { id ->
        repository.getRoutineSummaries(id).asLiveData()
    }

    fun load() {
        viewModelScope.launch {
            val child = repository.getChild() ?: return@launch
            _childId.value = child.id
        }
    }
}
