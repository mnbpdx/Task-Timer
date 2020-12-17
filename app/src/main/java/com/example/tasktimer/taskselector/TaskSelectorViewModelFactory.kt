package com.example.tasktimer.taskselector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasktimer.database.TaskDatabaseDao

class TaskSelectorViewModelFactory(
    private val taskDatabase: TaskDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskSelectorViewModel::class.java)) {
            return TaskSelectorViewModel(taskDatabase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}