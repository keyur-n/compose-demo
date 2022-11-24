package com.example.composedemo.ui.activity.event_handler

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProduceStateSnackbarSolution(){
    var counter = produceState(initialValue = 0){
        delay(3000)// Some network call or suspend function
        value=4
    }
    Column() {
        Text(text = "Snackbar will be removed immediately when counter condition changed")
        Button(onClick = {  }) {
            Text(text = "Click me $counter")
        }
        Button(onClick = {  }) {
            Text(text = "Also check code for other demo of Produce State")
        }
    }
}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun ProduceState2DemoSimilarToFlow(countUpto:Int): State<Int> {
    return produceState(initialValue = 0){
        while(value<countUpto){
            delay(1000L)// Some network call or suspend function
            value++
        }
    }
}
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun FlowSolution(countUpto:Int): State<Int> {
    return flow<Int>{
        var value=0
        while(value<countUpto){
            delay(1000L)// Some network call or suspend function
            value++
            emit(value  )
        }
    }.collectAsState(initial = 0)
}