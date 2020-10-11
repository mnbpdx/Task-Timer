package com.example.tasktimer.timer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.tasktimer.R
import com.example.tasktimer.databinding.FragmentTimerBinding

class TimerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentTimerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_timer, container, false)

        val viewModelFactory = TimerViewModelFactory()
        val timerViewModel = ViewModelProvider(this, viewModelFactory)
            .get(TimerViewModel::class.java)

        binding.timerViewModel = timerViewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}