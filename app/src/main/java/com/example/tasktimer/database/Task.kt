package com.example.tasktimer.database

import androidx.room.*

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Long = 0L,

    @ColumnInfo(name = "name")
    val name: String
)

data class TaskWithTaskEvents(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "taskId",
        entityColumn = "event_type_id"
    )
    val taskEvents: List<TaskEvent>
)