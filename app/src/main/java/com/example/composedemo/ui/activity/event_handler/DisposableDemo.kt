package com.example.composedemo.ui.activity.event_handler

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun DisposableDemoScreen(){
    Text(text = "Check Code -DisposableDemoScreen to understand lifecycler observer ")
}


@Composable
fun DisposableEffectIssue() {
    val lifecycleOwner = LocalLifecycleOwner.current
    val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            print("On Pause called")
        }
    }
    lifecycleOwner.lifecycle.addObserver(observer)
    //TODO: Issue-We need to dispose/remove observer or callback etc.
}

@Composable
fun DisposableEffectSolution() {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner, effect = {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {
                print("On Pause called")
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })
}