package com.example.stopwatch_with_coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {

    private val _time = MutableLiveData<Int>(0)   // seconds
    val time: LiveData<Int> = _time

    private var job: Job? = null
    private var isRunning = false

    fun start() {
        if (isRunning) return
        isRunning = true

        job = viewModelScope.launch {
            while (isRunning) {
                delay(1000)
                _time.value = (_time.value ?: 0) + 1
            }
        }
    }

    fun pause() {
        isRunning = false
        job?.cancel()
    }

    fun reset() {
        pause()
        _time.value = 0
    }
}
