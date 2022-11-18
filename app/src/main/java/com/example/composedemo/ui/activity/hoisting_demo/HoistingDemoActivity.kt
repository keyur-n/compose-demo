package com.example.composedemo.ui.activity.hoisting_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.ui.theme.ComposeDemoTheme

class HoistingDemoActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                HoistingScreen()
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, HoistingDemoActivity::class.java))
        }
    }
}


@Composable
private fun HoistingScreen(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        ParentScreen()
    }
}

@Composable
fun ParentScreen(){
    //Always user state in parent composable and pass it to child composable3
    val name by remember {
        mutableStateOf("")
    }
    ChildScreen(name = name, onChange = {

    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildScreen(name:String,onChange:(String) ->Unit){
    TextField(value = name, onValueChange = {
        onChange(it)
    })
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    ComposeDemoTheme {
        HoistingScreen()
    }
}
