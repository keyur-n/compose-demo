package com.example.composedemo.ui.activity.checkbox_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import com.example.composedemo.ui.activity.bottomsheet_demo.BottomsheetDemoActivity
import com.example.composedemo.ui.activity.bottomsheet_demo.BottomsheetDemoScreen
import com.example.composedemo.ui.theme.ComposeDemoTheme

class CheckboxDemoActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Surface {
                    CheckboxUi()

                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, CheckboxDemoActivity::class.java))
        }
    }
}


@Composable
fun CheckboxUi() {
    var isChecked by remember { mutableStateOf(false) }
    var genderState by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Checkbox(checked = isChecked, onCheckedChange = {
            isChecked = it
        })
        Spacer(modifier = Modifier.height(20.dp))
        CustomCheckboxUi()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomCheckboxUi() {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            elevation = 0.dp,
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White,
            border = BorderStroke( 1.dp,Color.Black),

        ) {
            Box(
                modifier = Modifier.background(
                    if (isChecked) Color.Green else Color.White
                ).clickable {
                    isChecked=!isChecked
                }
            ) {
                Icon(Icons.Default.Check, contentDescription = null, tint = Color.White)
//                else
//                    Icon(Icons.Default.Check, contentDescription = null, tint = Color.White)
            }
        }
        Text(text = "Accept all terms and condition", modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp))
    }

}