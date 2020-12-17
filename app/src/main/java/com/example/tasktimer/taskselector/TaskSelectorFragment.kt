package com.example.tasktimer.taskselector

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasktimer.R
import com.example.tasktimer.databinding.FragmentTaskSelectorBinding
import com.example.tasktimer.database.TaskDatabase
import com.example.tasktimer.taskeventdatabase.TaskEventDatabase

class TaskSelectorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTaskSelectorBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_task_selector, container, false)


        val application = requireNotNull(this.activity).application
        val database = TaskDatabase.getInstance(application).taskDatabaseDao
        val viewModelFactory = TaskSelectorViewModelFactory(database)
        val taskSelectorViewModel = ViewModelProvider(
            this, viewModelFactory).get(TaskSelectorViewModel::class.java)

        binding.taskSelectorViewModel = taskSelectorViewModel
        binding.lifecycleOwner = this

        taskSelectorViewModel.navigateToTimer.observe(viewLifecycleOwner) {
            findNavController().navigate(TaskSelectorFragmentDirections.actionTaskSelectorFragmentToTimerFragment())
        }


        return binding.root
    }



}