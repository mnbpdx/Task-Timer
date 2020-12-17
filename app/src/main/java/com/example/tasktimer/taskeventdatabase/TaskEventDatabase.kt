package com.example.tasktimer.taskeventdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tasktimer.database.Converters
import com.example.tasktimer.database.TaskEvent
import com.example.tasktimer.database.TaskEventDatabaseDao

@Database(entities = [TaskEvent::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TaskEventDatabase : RoomDatabase() {

    abstract val taskEventDatabaseDao: TaskEventDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: TaskEventDatabase? = null

        fun getInstance(context: Context): TaskEventDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TaskEventDatabase::class.java,
                        "task_event_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance

                }
                return instance
            }
        }
    }


}