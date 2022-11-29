package com.example.composedemo.ui.activity.viewmode_string_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedemo.ui.activity.pull_refresh_demo.PullRefreshDemoActivity
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch

/**
 * Demo for usig strig resource in viewmodel
 */
class ViewModelStringDemoActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ComposeDemoTheme() {
                Surface {
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(scaffoldState=scaffoldState){
                        it.calculateTopPadding()
                        SnackbarScreen(
                            scaffoldState = scaffoldState
                        )
                    }

                }
            }


        }
    }
    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ViewModelStringDemoActivity::class.java))
        }
    }
}
@Composable
fun SnackbarScreen(scaffoldState: ScaffoldState){
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val viewModel= viewModel<MaiViewModel>()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp),
       ) {
        Button(onClick = {

            val msg=viewModel.checkValidation("user")

            scope.launch {
                Toast.makeText(context,msg.asString2(context),Toast.LENGTH_LONG).show()
                scaffoldState.snackbarHostState.showSnackbar(msg.asString2(context),
                    actionLabel = "Done",SnackbarDuration.Long)
            }
        }) {
            Text(text = "Check validation")

        }
    }
}

// Function to generate a Toast
private fun mToast(context: Context,msg:String){
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}