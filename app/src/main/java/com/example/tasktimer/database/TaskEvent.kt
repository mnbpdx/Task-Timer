package com.example.tasktimer.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "task_event_table")
data class TaskEvent(
    @PrimaryKey(autoGenerate = true)
    val eventID: Long = 0L,

    @ColumnInfo(name = "event_type")
    val eventType: TimerType,

    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli
)

enum class TimerType(val value: String) {
    BRUSH_TEETH("brush_teeth"),
    SHOWER("shower"),
    GET_READY_FOR_BED("get_ready_for_bed"),
    MAKE_LUNCH("make_lunch")

}

class Converters {

    @TypeConverter
    fun stringToTimerType(string: String) : TimerType? {
        return enumValueOf<TimerType>(string.toUpperCase())
    }

    @TypeConverter
    fun  timerTypeToString(type: TimerType?) : String? {
        return type?.value
    }
}