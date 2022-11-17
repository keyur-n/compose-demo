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
fun NavigationThird(navController: NavHostController) {
    Surface {
        Column() {
            Text(text = "Hold your travel", modifier = Modifier.clickable {

            })
            Text(text = "Go back and you will be redirect to 1st screen. Because popUpTo used in second screen.", modifier = Modifier.padding(top = 16.dp))
            RegularButton(text = "Next") {
                navController.navigate(NavigationScreen.NavigationFour.route){
                    popUpTo(NavigationScreen.NavigationHome.route){
                        //inclusive=true -> This will remove First screen also, and exit activity
                        inclusive=true
                    }
                }
            }
        }
    }
}