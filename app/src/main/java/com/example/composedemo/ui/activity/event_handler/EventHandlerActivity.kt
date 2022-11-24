package com.example.composedemo.ui.activity.event_handler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedemo.ui.activity.DemoData
import com.example.composedemo.ui.theme.ComposeDemoTheme


class EventHandlerActivity : ComponentActivity() {
    var user = User("MTPL   ")
    private val interval = 10000L // 1 Second

//    private val handler: Handler = Handler()
//    var runnable = lazy {
//        Runnable {
//            Toast.makeText(this, "C'Mom no hands!", Toast.LENGTH_SHORT).show()
//            user = User("Updated after simne time")
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                val viewModel = viewModel<LaunchEffectViewModel>()
                EventHandlerScreen(viewModel, user, OnBackPressedDispatcher())
            }
        }
//        handler.postAtTime(runnable.value, System.currentTimeMillis() + interval);
//
//        handler.postDelayed(runnable.value, interval);

    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, EventHandlerActivity::class.java))
        }
    }
}
sealed class EventHandler{
    object Initial:EventHandler()
    object DisposableDemo:EventHandler()
    object SideEffectDemo:EventHandler()
    object LaunchedEffectDemo:EventHandler()
    object RememberUpdateDemo:EventHandler()
    object ProduceStateDemo:EventHandler()
    object DerivedStateDemo:EventHandler()
    object SnapshotFlowDemo:EventHandler()
}

@Composable
private fun EventHandlerScreen(viewModel: LaunchEffectViewModel, user: User,backPressedDispatcher:OnBackPressedDispatcher) {
    var screenEvent:EventHandler by remember {
        mutableStateOf(EventHandler.Initial)
    }
    val callback= remember {
        object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                if (screenEvent!=EventHandler.Initial){
                    screenEvent=EventHandler.Initial
                }
            }

        }
    }
    val context= LocalContext.current as Activity
    var isEnable by remember {
        mutableStateOf(false)
    }
    BackHandler() {
        if (screenEvent!=EventHandler.Initial){
            screenEvent=EventHandler.Initial
        }else{
//            isEnable=true
            context.finish()
        }
    }
    backPressedDispatcher.addCallback(callback)
    when(screenEvent){
        EventHandler.DisposableDemo -> {
            DisposableDemoScreen()
        }
        EventHandler.Initial -> {
            Column {

                Button(onClick = { screenEvent=EventHandler.DerivedStateDemo }) {
                    Text(text = "Derived State Demo")
                }
                Button(onClick = { screenEvent=EventHandler.LaunchedEffectDemo }) {
                    Text(text = "Launched Effect Demo")
                }
                Button(onClick = { screenEvent=EventHandler.SideEffectDemo }) {
                    Text(text = "Side Effect Demo")
                }
                Button(onClick = { screenEvent=EventHandler.DisposableDemo }) {
                    Text(text = "Disposable Effect Demo")
                }
                Button(onClick = { screenEvent=EventHandler.ProduceStateDemo }) {
                    Text(text = "Produce State Demo")
                }
                Button(onClick = { screenEvent=EventHandler.RememberUpdateDemo }) {
                    Text(text = "Remember Update State Demo")
                }
                Button(onClick = { screenEvent=EventHandler.SnapshotFlowDemo }) {
                    Text(text = "Snapshot Flow Demo")
                }
            }




        }
        EventHandler.LaunchedEffectDemo -> {
            LanchedEffectDemo(viewModel)
        }
        EventHandler.RememberUpdateDemo -> {
            RememberUpdateScreen()
        }
        EventHandler.SideEffectDemo -> {
            SideEffectScreen(user)
        }
        EventHandler.ProduceStateDemo -> {
            ProduceStateSnackbarSolution()
        }
        EventHandler.DerivedStateDemo -> {
            DerivedStateScreen()
        }
        EventHandler.SnapshotFlowDemo -> {
            SnapshotFlowScreen()
        }
    }


}



