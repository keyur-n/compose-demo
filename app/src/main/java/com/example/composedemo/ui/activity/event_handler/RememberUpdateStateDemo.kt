package com.example.composedemo.ui.activity.event_handler

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
fun RememberUpdateScreen(){
    Column() {
        TwoButtonScreenIssue()
        TwoButtonScreenSolution()
    }
}
@Composable
fun TwoButtonScreenIssue() {
    var buttonColour by remember {
        mutableStateOf("Unknown")
    }
//    DisposableEffectSolution()
    Column {
        Button(
            onClick = {
                buttonColour = "Red"
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red
            )
        ) {
            Text("Red Button")
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                buttonColour = "Black"
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black
            )
        ) {
            Text("Black Button", color = Color.White)
        }
        TimerIssue(buttonColour = buttonColour)

    }
}

@Composable
fun TimerIssue(
    buttonColour: String
) {
    val timerDuration = 5000L
    println("Composing timer with colour : $buttonColour")
    LaunchedEffect(key1 = Unit, block = {
        startTimer(timerDuration) {
            println("Timer ended")
            println("Last pressed button color was $buttonColour")
        }
    })
}

suspend fun startTimer(time: Long, onTimerEnd: () -> Unit) {
    delay(timeMillis = time)
    onTimerEnd()
}


@Composable
fun TwoButtonScreenSolution() {
    var buttonColour by remember {
        mutableStateOf("Unknown")
    }
    Column {
        Button(
            onClick = {
                buttonColour = "Red"
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Red
            )
        ) {
            Text("Red Button")
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                buttonColour = "Black"
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Black
            )
        ) {
            Text("Black Button", color = Color.White)
        }
        TimerSolution(buttonColour = buttonColour)
    }
}

@Composable
fun TimerSolution(
    buttonColour: String
) {
    val timerDuration = 5000L
    println("Composing timer with colour : $buttonColour")
    val buttonColorUpdated by rememberUpdatedState(newValue = buttonColour)
    LaunchedEffect(key1 = Unit, block = {
        startTimer(timerDuration) {
            println("Timer ended")
            println("[1] Last pressed button color is $buttonColour")
            println("[2] Last pressed button color is $buttonColorUpdated")
        }
    })
}