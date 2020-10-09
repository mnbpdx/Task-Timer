package com.example.tasktimer.taskselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.tasktimer.R
import com.example.tasktimer.databinding.FragmentTaskSelectorBinding

class TaskSelectorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTaskSelectorBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_task_selector, container, false)

        return binding.root
    }
}