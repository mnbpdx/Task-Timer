package com.example.tasktimer.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasktimer.database.TaskDatabaseDao

class TimerViewModelFactory(
    val database: TaskDatabaseDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            return TimerViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}