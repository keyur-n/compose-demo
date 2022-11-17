package com.example.composedemo.ui.activity.navigation_voyager

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior

class VoyagerNavigationActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Navigator(screen = HomeScreen,
                disposeBehavior = NavigatorDisposeBehavior(),
            onBackPressed = { currentScreen ->
                if (currentScreen is HomeScreen){
                    false
                }else{
                    true
                }
            })
        }
    }
    companion object{
        fun newIntent(context: Context){
            context.startActivity(Intent(context,VoyagerNavigationActivity::class.java))
        }
    }
}