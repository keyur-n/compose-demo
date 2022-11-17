package com.example.composedemo.ui.activity.navigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.ui.theme.ComposeDemoTheme

class NavigationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    startNavigationDemo()
                }
            }
        }
    }
    companion object{
        fun newIntent(context: Context){
            context.startActivity(Intent(context,NavigationActivity::class.java))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    ComposeDemoTheme {
        startNavigationDemo()
    }
}