package com.example.tasktimer.taskselector

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasktimer.databinding.ListItemTaskBinding
import com.example.tasktimer.taskdatabase.Task

//TODO Generalized DataItems is half finished in this file. Add when in OCV, add viewTypes + checks

class TaskAdapter : ListAdapter<DataItem,
        RecyclerView.ViewHolder>(TaskDiffCallback()) {


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is TaskViewHolder -> {
                val item = getItem(position) as DataItem.TaskItem
                holder.bind(item.task)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TaskViewHolder.from(parent)
    }


    class TaskViewHolder private constructor(val binding: ListItemTaskBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Task) {
            binding.task = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TaskViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemTaskBinding.inflate(layoutInflater, parent, false)
                return TaskViewHolder(binding)
            }
        }
    }
}



class TaskDiffCallback: DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }

}

sealed class DataItem {
    abstract val id: Long

    data class TaskItem(val task: Task): DataItem() {
        override val id = task.id
    }
}

/*
Need to talk with Matt or Ethan about architecture of this app. How should I handle the Task objects?
Should they be a simple class, unconnected to a database? Probably not, since eventually I want
to be able to add new Tasks during runtime.

So, create two separate databases then? One for Tasks, one for TaskEvents (of all task types)?
If I do this, do I need a separate

Then I can put the Tasks in ViewHolders for RecyclerView here in the Adapter.

Does this seem like a reasonable way of structuring? I'll probably put an average time value with
each Task. Will this structure allow one database to query another like that easily?

How does all this relate to Lifecycles? Like, where should I put stuff so stuff stays alive when it
should be?

Why doesn't my back button work on the timer screen?
 */