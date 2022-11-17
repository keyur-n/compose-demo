package com.example.composedemo.ui.activity.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composedemo.ui.view.RegularButton

@Composable
fun NavigationSecond(navController: NavHostController, data: String?) {
    Surface {
        Column() {
            Text(text = "Congratulations, You travel to the moon via navigation")
            Text(text = "$data")
            Text(text = "Click Back to goto previous screen.", modifier = Modifier.padding(top = 16.dp))
            RegularButton(text = "Next") {
                navController.navigate(NavigationScreen.NavigationThird.route){
                    popUpTo(NavigationScreen.NavigationHome.route)
                }
            }
        }
    }
}