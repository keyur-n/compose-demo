package com.example.composedemo.ui.activity.box_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.ui.core.Text
import com.example.composedemo.R
import com.example.composedemo.ui.theme.ComposeDemoTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.activity.checkbox_demo.CustomCheckboxUi

class BoxDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface() {
                    BoxScreen()
                }

            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, BoxDemoActivity::class.java))
        }
    }
}


@Composable
fun BoxScreen() {

    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.purple_500)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Accept all terms and condition", modifier = Modifier.padding(8.dp),
            color = Color.Red
        )
        Text(
            text = "Top Center",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopCenter),
            color = Color.Yellow
        )
        Text(
            text = "Top Start",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart),
            color = Color.Yellow
        )
        Text(
            text = "Top End",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopEnd),
            color = Color.Cyan
        )
        Text(
            text = "Bottom Center",
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomCenter),
            color = Color.White
        )
//        Text(text = "Welcome to Box Demo2")
    }

}

@Preview(showBackground = true)
@Composable
private fun BoxScreenPreview() {
    BoxScreen()
}

