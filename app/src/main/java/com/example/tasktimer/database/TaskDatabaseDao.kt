package com.example.tasktimer.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDatabaseDao {

    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getTaskList(): LiveData<List<Task>>

    @Transaction
    @Query("SELECT * from task_table")
    fun getTasksWithEvents(): List<TaskWithTaskEvents>
}