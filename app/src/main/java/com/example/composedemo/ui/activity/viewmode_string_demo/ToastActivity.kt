package com.example.composedemo.ui.activity.viewmode_string_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.composedemo.ui.theme.ComposeDemoTheme

class ToastActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeDemoTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val context = LocalContext.current
                    Button(
                        onClick = {
                            Toast.makeText(context, "This is a Toast. Yay!", Toast.LENGTH_SHORT).show()
                        },
                    ) {
                        Text("Show Toast")
                    }
                }
            }
        }
    }
    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ToastActivity::class.java))
        }
    }
}