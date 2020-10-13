package com.example.tasktimer.timer

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.*
import com.example.tasktimer.database.TaskDatabaseDao
import com.example.tasktimer.database.TaskEvent
import kotlinx.coroutines.launch

class TimerViewModel(
    val database : TaskDatabaseDao) : ViewModel() {

    private val timer : CountDownTimer

    private var _currentTime = MutableLiveData<Long>()
    val currentTime : LiveData<Long>
        get() = _currentTime

    val currentTimeString = Transformations.map(currentTime){time ->
        DateUtils.formatElapsedTime(time)
    }

    companion object {
        private const val MAX_TIME = 86400000L

        private const val ONE_SECOND = 1000L
    }

    init {
        timer = object : CountDownTimer(MAX_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (MAX_TIME - millisUntilFinished) / 1000
            }

            override fun onFinish() {
                //TODO: Add errorz check in case of runaway timer
            }
        }
    }

    private suspend fun insert(task : TaskEvent) {
        database.insert(task)
    }

    fun stopTask() {
        timer.cancel()
    }

    fun startTask() {
        viewModelScope.launch {
            val newTask = TaskEvent()

            insert(newTask)

            timer.start()
        }

    }
}