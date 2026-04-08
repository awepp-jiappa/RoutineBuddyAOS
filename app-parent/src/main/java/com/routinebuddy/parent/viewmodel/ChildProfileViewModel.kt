package com.routinebuddy.parent.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.routinebuddy.parent.data.entity.ChildEntity
import com.routinebuddy.parent.repository.ParentRepository
import kotlinx.coroutines.launch

class ChildProfileViewModel(private val repository: ParentRepository) : ViewModel() {
    private val _child = MutableLiveData<ChildEntity?>()
    val child: LiveData<ChildEntity?> = _child

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun loadChild() {
        viewModelScope.launch {
            _child.value = repository.getChild()
        }
    }

    fun saveChild(name: String, ageGroup: String, onSuccess: () -> Unit) {
        if (name.isBlank()) {
            _error.value = "Child name is required"
            return
        }
        viewModelScope.launch {
            repository.saveChild(name.trim(), ageGroup)
            _error.value = null
            onSuccess()
        }
    }
}
