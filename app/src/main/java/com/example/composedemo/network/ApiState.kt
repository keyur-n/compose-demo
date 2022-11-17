package com.example.composedemo.network

import com.example.composedemo.model.Entry

sealed class ApiState {
    class Success(val list: ArrayList<Entry>):ApiState()
    class Failure(val message: String):ApiState()
    object Loading : ApiState()
    object Empty : ApiState()
}