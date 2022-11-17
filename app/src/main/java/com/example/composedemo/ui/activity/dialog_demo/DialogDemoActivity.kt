package com.example.composedemo.ui.activity.dialog_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.composedemo.ui.activity.checkbox_demo.CheckboxDemoActivity
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.BoldText
import com.example.composedemo.ui.view.RegularButton
import com.example.composedemo.ui.view.RegularText

class DialogDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme() {
                Surface {
                    DialogScreen()
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, DialogDemoActivity::class.java))
        }
    }
}


@Composable
fun DialogScreen() {
    var isDialog by remember { mutableStateOf(false) }
    var isAlertDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isDialog = true }) {
            RegularText(text = "Open Loading Dialog")
        }
        Button(onClick = { isAlertDialog = true }) {
            RegularText(text = "Open Alert Dialog")
        }
    }

    if (isDialog) {
        Dialog(
            onDismissRequest = { isDialog = false },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Card(modifier = Modifier.padding(8.dp)) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

        }
    }

    if (isAlertDialog) {
        Column() {
            AlertDialog(onDismissRequest = { /*TODO*/ },
                title = {
                    BoldText(text = "Success")
                },
                text = {
                    RegularText(text = "You successfully completed dialog course.")
                },
                modifier = Modifier.fillMaxWidth(),
                buttons = {
                    Button(
                        onClick = { isAlertDialog = false },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        RegularText(text = "OK")
                    }
                })
        }
    }
}

