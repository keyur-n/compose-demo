package com.example.composedemo.ui.activity.retrofit_demo

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composedemo.ui.callback.APIInterface
import com.example.composedemo.model.EntryResponse
import com.example.composedemo.network.APIClient.client
import com.example.composedemo.network.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call


class HomeViewModel : ViewModel() {
    val listEntry: MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    fun callApiEntry() = viewModelScope.launch(Dispatchers.IO) {
        try {
            listEntry.value = ApiState.Loading
            val apiInterface = client!!.create(APIInterface::class.java)
            val call: Call<EntryResponse> = apiInterface.callApiEntry()
            val response = call.execute()
            if (response.isSuccessful) {
                listEntry.value = ApiState.Success(response.body().entries)
            } else {
                listEntry.value = ApiState.Failure("Error")
            }

        } catch (e: Exception) {
            Log.e("HomeViewModelException",e.message?:"No messsage");
        }
    }
}