package com.example.composedemo.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun RegularButton(text:String, onClicked: () -> Unit,){
    Button(
        modifier = Modifier.padding(vertical = 24.dp),
        onClick = onClicked
    ) {
        Text(text)
    }
}