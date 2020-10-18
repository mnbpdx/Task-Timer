package com.example.tasktimer.timer

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tasktimer.database.TaskDatabaseDao

class TimerViewModelFactory(
    private val database: TaskDatabaseDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            return TimerViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}