package com.example.composedemo.ui.activity.event_handler

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector4D
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun LanchedEffectDemo(viewModel: LaunchEffectViewModel) {
    val counter = remember {
        mutableStateOf(0)
    }

    LaunchedEffectSharedFlowDemo(viewModel = viewModel)
    val isClicked = remember {
        mutableStateOf(false)
    }
    val color = remember { androidx.compose.animation.Animatable(Color.Gray) }

    LaunchedEffectAnimationDemo(counter = counter.value, isClicked.value, color)
    Column {
        Button(onClick = {
            counter.value++
            isClicked.value = !isClicked.value
        }) {
            Text(text = "Update Counter ${counter.value}")
        }

//        Box(Modifier.fillMaxSize().background(color.value))
        Box(
            Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(16.dp)
                .background(color.value)
        )

        Box(modifier = Modifier.weight(1f)){
            LaunchEffectSnackbarIssue()
        }
        Box(modifier = Modifier.weight(1f)){
            LaunchEffectSnackbarSolution()
        }
    }
}

@Composable
fun LaunchedEffectSharedFlowDemo(viewModel: LaunchEffectViewModel) {
    LaunchedEffect(key1 = true) {
        //Sharedflow will be collected once only as it is inside LaunchedEffect, otherwise it will collect every time composition change
        viewModel.sharedFlow.collect { event ->
            when (event) {
                is LaunchEffectViewModel.SharedEvent.ShowSnackbar -> {

                }
            }
        }
    }
}

@Composable
fun LaunchedEffectAnimationDemo(
    counter: Int,
    value: Boolean,
    color: Animatable<Color, AnimationVector4D>
) {
    val animatable = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = counter) {
        animatable.animateTo(
            counter.toFloat(), animationSpec = tween(
                durationMillis = 2000, delayMillis = 500,
                easing = LinearEasing
            )
        )
    }
    LaunchedEffect(key1 = value) {
        color.animateTo(
            if (value) Color.Green else Color.Red,
            animationSpec = tween(
                durationMillis = 2000, delayMillis = 500,
                easing = LinearEasing
            )
        )
    }

//    Box(Modifier.fillMaxSize().background(color.value))
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LaunchEffectSnackbarIssue(){
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(scaffoldState = scaffoldState, modifier = Modifier.fillMaxWidth()) {
        it.calculateTopPadding()
        var counter by remember {
            mutableStateOf(0)
        }
        if (counter%5==0 && counter!=0){
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Hello")
            }
        }
        Column {
            Text(text = "Snackbar will not be removed when counter condition changed")
            Button(onClick = { counter++ }) {
                Text(text = "Click me $counter")
            }
        }

    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun LaunchEffectSnackbarSolution(){
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, modifier = Modifier.fillMaxWidth()) {
        it.calculateTopPadding()
        var counter by remember {
            mutableStateOf(0)
        }
        if (counter%5==0 && counter!=0){
            LaunchedEffect(key1 = scaffoldState.snackbarHostState){
                scaffoldState.snackbarHostState.showSnackbar("Hello")
            }

        }
        Column() {
            Text(text = "Snackbar will be removed immediately when counter condition changed")
            Button(onClick = { counter++ }) {
                Text(text = "Click me $counter")
            }
        }

    }
}
