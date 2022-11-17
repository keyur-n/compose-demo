package com.example.composedemo.ui.activity.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

sealed class NavigationScreen(val route:String){
    object NavigationHome:NavigationScreen("first")
    object NavigationSecond:NavigationScreen("{data}/second"){
        fun createRoute(data:String)="${data}/second"
    }
    object NavigationThird:NavigationScreen("third")
    object NavigationFour:NavigationScreen("four")
}

@Composable
fun startNavigationDemo(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = NavigationScreen.NavigationHome.route){
        composable(NavigationScreen.NavigationHome.route){
            NavigationHome(navController)
        }
        composable(NavigationScreen.NavigationSecond.route){
            val data=it.arguments?.getString("data")
            NavigationSecond(navController,data)
        }
        composable(NavigationScreen.NavigationThird.route){
            NavigationThird(navController)
        }
        composable(NavigationScreen.NavigationFour.route){
            NavigationFour()
        }
    }
}