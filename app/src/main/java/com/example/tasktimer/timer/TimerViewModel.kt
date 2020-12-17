package com.example.tasktimer.timer

import android.app.Application
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_SHOW_DATE
import android.text.format.DateUtils.FORMAT_SHOW_TIME
import androidx.lifecycle.*
import com.example.tasktimer.database.Task
import com.example.tasktimer.database.TaskEventDatabaseDao
import com.example.tasktimer.database.TaskEvent
import com.example.tasktimer.database.TaskType
import kotlinx.coroutines.launch

class TimerViewModel(
    private val task: Task,
    private val eventDatabase: TaskEventDatabaseDao,
    application: Application
) : AndroidViewModel(application) {



    private var _currentTime = MutableLiveData<Long>()
//    val currentTime : LiveData<Long> // Do I need this if I'm accessing currentTime publicly
//        get() = _currentTime         // through currentTimeString anyway?

    val currentTimeString: LiveData<String> = Transformations.map(_currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    //private var newestTaskEvent = MutableLiveData<TaskEvent?>()

    //Temporary string to test database
    val newestTaskEventString: LiveData<String> = Transformations.map(eventDatabase.lastUpdateStream(
        task.taskId)) {
        "Start Time: " + DateUtils.formatDateTime(getApplication(), it?.startTimeMilli ?: 0,
            FORMAT_SHOW_TIME or FORMAT_SHOW_DATE) + "\n" +
        "End Time: " + DateUtils.formatDateTime(getApplication(), it?.endTimeMilli ?: 0,
            FORMAT_SHOW_TIME or FORMAT_SHOW_DATE) + "\n" +
        "Elapsed Time: " + DateUtils.formatElapsedTime(((it?.endTimeMilli ?: 0)
                - (it?.startTimeMilli ?: 0)) / ONE_SECOND)
    }

    private val timer: CountDownTimer

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
                //TODO: Add error check in case of runaway timer
            }
        }

//        initializeNewestTaskEvent()
    }

    private suspend fun insert(task: TaskEvent) {
        eventDatabase.insert(task)
    }

    private suspend fun update(task: TaskEvent) {
        eventDatabase.update(task)
    }

//    private fun initializeNewestTaskEvent() {
//        viewModelScope.launch {
//            newestTaskEvent.value = getNewestTaskEvent()
//        }
//    }


//    private suspend fun getNewestTaskEvent() : TaskEvent? {
//        var task = database.getTopEvent()
//        if (task?.startTimeMilli != task?.endTimeMilli) {
//            task = null
//        }
//        return task
//    }

    fun stopTask() {
        timer.cancel()
        val endTime = System.currentTimeMillis()
        viewModelScope.launch {
           eventDatabase.getTopEvent(task.taskId)?.let { event ->
                event.endTimeMilli = endTime
                this@TimerViewModel.update(event)
            }
        }
    }

    fun startTask() {
        viewModelScope.launch {
            val newTask = TaskEvent(eventTypeId = task.taskId)

            insert(newTask)

            timer.start()

//            val temp = getNewestTaskEvent()
//            newestTaskEvent.value = temp
        }

    }
}