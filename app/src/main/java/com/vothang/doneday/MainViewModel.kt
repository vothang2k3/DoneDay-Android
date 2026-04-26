package com.vothang.doneday

import android.util.Log
import androidx.lifecycle.ViewModel
import com.vothang.doneday.repository.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val taskRepo1 : TaskRepo,
    private val taskRepo2 : TaskRepo
) : ViewModel() {
    init {
        Log.d("MainViewModel", "init $taskRepo1")
        Log.d("MainViewModel", "init $taskRepo2")
    }
}