package com.example.tasktimer.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tasktimer.database.TaskEvent

@Dao
interface TaskEventDatabaseDao {

    @Insert
    suspend fun insert(event: TaskEvent)

    @Update
    suspend fun update(event: TaskEvent)

    @Query("SELECT * FROM task_event_table WHERE event_type_id = :type ORDER BY eventId DESC LIMIT 1")
    fun lastUpdateStream(type: Long) : LiveData<TaskEvent?>

    @Query("SELECT * FROM task_event_table WHERE event_type_id = :type ORDER BY eventId DESC LIMIT 1")
    suspend fun getTopEvent(type: Long) : TaskEvent?
}

//TODO ch