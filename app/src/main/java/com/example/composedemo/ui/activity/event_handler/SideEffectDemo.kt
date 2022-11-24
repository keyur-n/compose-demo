package com.example.composedemo.ui.activity.event_handler

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlin.random.Random


data class User(var userName: String)

fun getRandomString(length: Int): String {
    val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
    return (1..length)
        .map { allowedChars.random() }
        .joinToString("")
}

@Composable
fun SideEffectScreen(userN: User) {
    var user=User("Check Code & Logs to understand ")
    sideEffectDemo(user = user)
    Column {
        Text(text = user.userName)
        Button(onClick = {
            val randomCount = Random(20).nextInt(5, 10)
            val randomName = getRandomString(randomCount)
            user = User(randomName)
            Log.e("SideEffectScreen", "Id=$randomName")
        }) {
            Text(text = "Update Randome Name")
        }
    }
}

@Composable
fun sideEffectDemo(user: User): FirebaseAnalytics {
    val analytics: FirebaseAnalytics = remember {
        Firebase.analytics
    }

    // On every successful composition, update FirebaseAnalytics with
    // the userName from the current User, ensuring that future analytics
    // events have this metadata attached
    SideEffect {
        Log.e("sideEffectSolution", "1. This block will executed after composing finished UserName=${user.userName}")
        Log.e("sideEffectSolution", "2. Handle non composable things inside SideEffect i.e. increment counter etc. block=${user.userName}")
        analytics.setUserProperty("userName", user.userName)
    }
    Log.e("sideEffectSolution", "UserName=ObjectClosed")
    return analytics
}