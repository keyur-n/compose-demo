package com.example.composedemo.ui.activity.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun NavigationFour(){
    Surface {
        Column() {
            Text(text = "Your journey complete here", modifier = Modifier.clickable {

            })
            Text(text = "Do you want to exit activity?\n Click back button.\ninclusive=true do the trick.", modifier = Modifier.padding(top = 16.dp))
        }
    }
}