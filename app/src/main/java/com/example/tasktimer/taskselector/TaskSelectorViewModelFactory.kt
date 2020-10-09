package com.example.tasktimer.taskselector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TaskSelectorViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskSelectorViewModel::class.java)) {
            return TaskSelectorViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}