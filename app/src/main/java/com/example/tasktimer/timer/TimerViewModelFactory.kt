package com.example.tasktimer.timer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasktimer.database.Task
import com.example.tasktimer.database.TaskEventDatabaseDao

class TimerViewModelFactory(
    private val task: Task,
    private val eventDatabase: TaskEventDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            return TimerViewModel(task, eventDatabase, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}