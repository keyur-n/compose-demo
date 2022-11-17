package com.example.composedemo.ui.activity.radio

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class RadioButtonDemoActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                RadioButtonUi()
            }
        }
    }

    companion object{
        fun newIntent(context: Context){
            context.startActivity(Intent(context,RadioButtonDemoActivity::class.java))
        }
    }
}

@Composable
fun RadioButtonUi(){
    val genderlist by remember { mutableStateOf(listOf("Male","Female","Other")) }
    var genderState by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            genderlist.forEach {
                Row {
                    Text(text = it, modifier = Modifier.align(CenterVertically))
                    RadioButton(selected = genderState == it, onClick = {
                        genderState = it
                    })
                }
            }
        }

    }
}