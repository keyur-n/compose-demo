package com.example.composedemo.ui.activity.toggle_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.R
import com.example.composedemo.ui.theme.ComposeDemoTheme

class ToggleDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Surface(
            ) {
                ToggleScreen()
            }
        }
    }


    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ToggleDemoActivity::class.java))
        }
    }
}


@Composable
private fun ToggleScreen(modifier: Modifier = Modifier) {
    var isToggle by remember { mutableStateOf(false) }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
        androidx.compose.material.Card(
            shape = RoundedCornerShape(40.dp),
            elevation = 0.dp,
        ) {
            Box(
                modifier = Modifier
                    .background(if (isToggle) Color.Magenta else Color.Yellow)
                    .clickable {
                        isToggle = !isToggle

                    },
                contentAlignment = Alignment.Center
            ) {
                if (isToggle) {
                    Row(modifier = Modifier.padding(5.dp)) {
                        Text(
                            text = "On", style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400,
                            ), color = Color.Black, modifier = Modifier
                                .padding(10.dp)
                                .align(CenterVertically)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_power_on),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                } else {
                    Row(modifier = Modifier.padding(5.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_power_off),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.padding(10.dp)

                        )
                        Text(
                            text = "Off", style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.W400,
                            ), color = Color.Black, modifier = Modifier
                                .padding(10.dp)
                                .align(CenterVertically)
                        )

                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ToggleScreenPreview() {
    ComposeDemoTheme {
        ToggleScreen()
    }
}
