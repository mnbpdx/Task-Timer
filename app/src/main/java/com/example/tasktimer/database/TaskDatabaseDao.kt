package com.example.tasktimer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDatabaseDao {

    @Insert
    suspend fun insert(event: TaskEvent)

    @Update
    suspend fun update(event: TaskEvent)

    @Query("SELECT * FROM task_event_table WHERE event_type = :type ORDER BY eventID DESC LIMIT 1")
    fun lastUpdateStream(type: String) : LiveData<TaskEvent?>

    @Query("SELECT * FROM task_event_table WHERE event_type = :type ORDER BY eventID DESC LIMIT 1")
    suspend fun getTopEvent(type: String) : TaskEvent?
}