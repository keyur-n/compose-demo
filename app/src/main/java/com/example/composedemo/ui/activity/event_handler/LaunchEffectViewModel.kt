package com.example.composedemo.ui.activity.event_handler

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LaunchEffectViewModel:ViewModel() {
    private val _sharedFlow= MutableSharedFlow<SharedEvent>()
    val sharedFlow= _sharedFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _sharedFlow.emit(SharedEvent.ShowSnackbar("Hello World"))
        }
    }

    sealed class SharedEvent{
        data class ShowSnackbar(val message:String):SharedEvent()
    }
}