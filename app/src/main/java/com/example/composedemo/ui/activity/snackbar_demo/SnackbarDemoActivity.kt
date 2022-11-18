package com.example.composedemo.ui.activity.snackbar_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import com.example.composedemo.ui.activity.bottomsheet_demo.BottomsheetDemoScreen
import com.example.composedemo.ui.activity.image_capture.ImageCaptureActivity
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch

class SnackbarDemoActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Surface {
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(scaffoldState=scaffoldState){
                        it.calculateTopPadding()
                        SnackbarScreen(scaffoldState = scaffoldState)
                    }

                }
            }

        }
    }
    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, SnackbarDemoActivity::class.java))
        }
    }
}

@Composable
fun SnackbarScreen(scaffoldState: ScaffoldState){
    val scope = rememberCoroutineScope()
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp),
    horizontalArrangement = Arrangement.Center) {
        Button(onClick = {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar("Congratulations SnackBar showing.",actionLabel = "Done",SnackbarDuration.Long)
            }
        }) {
            Text(text = "Show SnackBar")

        }
    }
}




@Preview
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        SnackbarScreen(rememberScaffoldState())
    }
}