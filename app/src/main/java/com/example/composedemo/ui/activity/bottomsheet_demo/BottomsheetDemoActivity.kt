package com.example.composedemo.ui.activity.bottomsheet_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.example.composedemo.Greeting
import com.example.composedemo.ui.activity.image_picker.PickImageFromGallery
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.RegularText

class BottomsheetDemoActivity:ComponentActivity(){
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Surface {
                    BottomSheetNavigator() {
                        Navigator(screen = BottomsheetDemoScreen)
                    }

                }
            }
        }
    }
    companion object{
        fun newIntent(context: Context){
            context.startActivity(Intent(context,BottomsheetDemoActivity::class.java))
        }
    }
}

object BottomsheetDemoScreen:Screen{
    @Composable
    override fun Content() {
        val navigator= LocalBottomSheetNavigator.current
        Button(onClick = { navigator.show(screen = BottomsheetScreen())}) {
            Text(text = "Open Bottomsheet")
        }
    }

}

data class BottomsheetScreen(val  names: List<String> = List(10) { "$it" }):Screen{
    @Composable
    override fun Content() {
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items= names) { name ->
                val navigator= LocalBottomSheetNavigator.current
                Row() {
                    IconButton(onClick = {
                        navigator.hide()
                    }) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                    RegularText(text = "Data $name")
                }

            }
        }
    }

}


@Preview
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        Navigator(screen = BottomsheetDemoScreen)
    }
}