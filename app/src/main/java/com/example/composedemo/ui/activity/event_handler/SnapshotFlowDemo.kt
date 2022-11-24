package com.example.composedemo.ui.activity.event_handler

import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull

@Composable
fun SnapshotFlowScreen() {
    val scaffoldState = rememberScaffoldState()
    Text(text = "Check Code to understand")
    SnapshotDemo(scaffoldState)
}

/**
 * Method to demo for understand snapshotflow
 * When we have state and we construct flow whenever composed state changes
 */
@Composable
fun SnapshotDemo(scaffoldState: ScaffoldState) {
    LaunchedEffect(key1 = scaffoldState) {
        snapshotFlow { scaffoldState.snackbarHostState }
            .mapNotNull { it.currentSnackbarData?.message }
            .distinctUntilChanged()
            .collect { message ->
                println("A message was shown to user was $message by snackbar")
            }

    }
}