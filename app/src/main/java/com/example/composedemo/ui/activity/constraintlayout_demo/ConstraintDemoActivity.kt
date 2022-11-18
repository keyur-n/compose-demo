package com.example.composedemo.ui.activity.constraintlayout_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composedemo.R
import com.example.composedemo.ui.activity.DemoData
import com.example.composedemo.ui.theme.ComposeDemoTheme

class ConstraintLayoutDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintDemoScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ConstraintLayoutDemoActivity::class.java))
        }
    }
}


@Composable
private fun ConstraintDemoScreen(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (username, tech, city) = createRefs()

        Text(text = "KN1010",
            modifier = Modifier.constrainAs(username) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(parent.top)
            }
        )
        Text(text = "Android",
            modifier = Modifier.constrainAs(tech) {
                top.linkTo(username.bottom)
                start.linkTo(parent.start)
            }
        )
        Text(text = "Ahmedabad",
            modifier = Modifier.constrainAs(city) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(tech.bottom, margin = 16.dp)
            }
        )
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeDemoTheme {
        ConstraintDemoScreen(Modifier.fillMaxSize())
    }
}
