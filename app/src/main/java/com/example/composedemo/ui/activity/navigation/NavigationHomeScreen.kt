package com.example.composedemo.ui.activity.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composedemo.ui.view.RegularButton

@Composable
fun NavigationHome(navHostController: NavHostController){
    Surface {

        Column {
            Text(text = "Welcome to Navigation Demo")
            RegularButton(text = "Next") {
                navHostController.navigate(NavigationScreen.NavigationSecond.createRoute("Data Passed from Home"))
            }
        }
    }
}