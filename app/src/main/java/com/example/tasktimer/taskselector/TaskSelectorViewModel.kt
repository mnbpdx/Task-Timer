package com.example.tasktimer.taskselector

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskSelectorViewModel() : ViewModel() {

    private var _displayBushTeethToast = MutableLiveData<Boolean>()
    val displayBrushTeethToast: LiveData<Boolean>
        get() = _displayBushTeethToast


    fun brushTeethToast() {
        _displayBushTeethToast.value = true
    }

}