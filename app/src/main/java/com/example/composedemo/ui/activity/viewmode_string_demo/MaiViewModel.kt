package com.example.composedemo.ui.activity.viewmode_string_demo

import androidx.lifecycle.ViewModel
import com.example.composedemo.R

class MaiViewModel:ViewModel() {
    private val MIN_LENGTH_NAME=5
    fun checkValidation(userName:String): UiText.StringResource {
       return if (userName.length<MIN_LENGTH_NAME){
            UiText.StringResource(resId = R.string.err_user_name,MIN_LENGTH_NAME)
        }else{
            UiText.StringResource(resId = R.string.msg_user_name_success)
        }
    }
}