package com.example.composedemo.ui.activity.hoisting_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.R
import com.example.composedemo.ui.theme.ComposeDemoTheme

class HoistingDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                ParentScreen()
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
fun ParentScreen() {
    //Always user state in parent composable and pass it to child composable3
    var name by remember {
        mutableStateOf("")
    }
    ChildScreen(name = name, onChange = {
        name = it

    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildScreen(name: String, onChange: (String) -> Unit) {

    TextField(value = name, onValueChange = {
        onChange(it)
    })
}

@Composable
fun ParentScreenStateLess() {
    //Always user state in parent composable and pass it to child composable3
    Column() {
        Text(text = "adsas")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "adasasdqwe")
        }
        Image(painter = painterResource(id = R.drawable.ic_welcome_1), contentDescription = null)
        ChildScreenStateFul()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChildScreenStateFul() {
    var name by remember {
        mutableStateOf("")
    }
    TextField(value = name, onValueChange = {
        name = it
    })
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    ComposeDemoTheme {
        ParentScreen()
    }
}
