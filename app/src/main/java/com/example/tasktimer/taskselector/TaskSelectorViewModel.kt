package com.example.tasktimer.taskselector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskSelectorViewModel : ViewModel() {

    private var _navigateToTimer = MutableLiveData<Boolean>()
    val navigateToTimer: LiveData<Boolean>
        get() = _navigateToTimer


    fun startTimer() {
        _navigateToTimer.value = true
    }

}