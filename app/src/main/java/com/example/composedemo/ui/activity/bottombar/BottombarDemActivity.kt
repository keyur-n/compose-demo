package com.example.composedemo.ui.activity.bottombar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.ui.theme.ComposeDemoTheme

class BottombarDemActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            BottomNavigation(navController = nav)
            ComposeDemoTheme() {
                MainScreenView()
            }

        }
    }
    companion object{
        fun newIntent(context: Context){
            context.startActivity(Intent(context,BottombarDemActivity::class.java))
        }
    }
}

@Composable
fun MainScreenView(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
    ) {
        it.calculateTopPadding()
        NavigationGraph(navController = navController)
    }
}