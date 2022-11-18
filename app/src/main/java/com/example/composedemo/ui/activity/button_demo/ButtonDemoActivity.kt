package com.example.composedemo.ui.activity.button_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.RegularButton

class ButtonDemoActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                ButtonScreen()
            }
        }
    }
    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ButtonDemoActivity::class.java))
        }
    }
}

@Composable
private fun ButtonScreen(){
    Column(modifier = Modifier.fillMaxSize()) {
        RegularButton(text = "Continue", onClicked = { })
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = RectangleShape,
            onClick = {

            }
        ) {
            Text("Rectangle Button")
        }
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = CircleShape,
            onClick = {

            }
        ) {
            Text("Circle Button")
        }
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = CutCornerShape(16.dp),
            onClick = {

            }
        ) {
            Text("CutCorner Button")
        }
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = {

            }
        ) {
            Text("RoundedCorner Button")
        }
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(4.dp, Color.Green),
            onClick = {

            }
        ) {
            Text("RoundedCorner Stroke Button")
        }
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White,

            ),
            onClick = {

            }
        ) {
            Text("Button Custom Color")
        }
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = false,
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Black,

                ),
            onClick = {

            }
        ) {
            Text("Disabled Button")
        }
        //Check Property elevation
        Button(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = CircleShape,
            elevation = androidx.compose.material3.ButtonDefaults.buttonElevation(pressedElevation = 10.dp),
            onClick = {

            }
        ) {
            Text("Elevation On Button Click")
        }
        TextButton(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = CircleShape,
            onClick = {

            }
        ) {
            Text("TextButton")
        }
        OutlinedButton(
            modifier = Modifier.padding(vertical = 8.dp),
            shape = CircleShape,
            onClick = {

            }
        ) {
            Text("OutlinedButton")
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun ButtonScreenPreview(){
    Surface() {
        ButtonScreen()
    }

}