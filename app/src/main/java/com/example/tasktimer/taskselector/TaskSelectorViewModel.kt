package com.example.tasktimer.taskselector

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasktimer.database.Task
import com.example.tasktimer.database.TaskDatabaseDao
import com.example.tasktimer.database.TaskWithTaskEvents
import kotlinx.coroutines.launch

class TaskSelectorViewModel(
    private val taskDatabase: TaskDatabaseDao,
) : ViewModel() {

    private var _navigateToTimer = MutableLiveData<Boolean>()
    val navigateToTimer: LiveData<Boolean>
        get() = _navigateToTimer


    fun navigateToTimer() {
        _navigateToTimer.value = true
    }

    init {
        viewModelScope.launch {
            testInsert()
        }
    }

    data class TaskListItemViewObject(
        val task: Task,
        val taskTimeAverage: Long
    )

    fun getTasksWithTasksEvents(): List<TaskListItemViewObject> =
        taskDatabase.getTasksWithEvents().map { taskWithTaskEvents ->
            val task = taskWithTaskEvents.task
            val average = taskWithTaskEvents.taskEvents.sumOf { it.endTimeMilli - it.startTimeMilli }
            TaskListItemViewObject(task, average)
        }

    private suspend fun testInsert() {
        val showerTask = Task(name = "shower")
        taskDatabase.insert(showerTask)

        val lunchTask = Task(name = "make_lunch")
        taskDatabase.insert(lunchTask)
    }

}