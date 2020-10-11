package com.example.tasktimer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update

@Dao
interface TaskDatabaseDao {

    @Insert
    suspend fun insert(event: TaskEvent)

    @Update
    suspend fun update(event: TaskEvent)
}