package com.example.composedemo.ui.activity.toolbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.composedemo.ui.theme.ComposeDemoTheme

class ToolbarActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Greeting2("Android")
                    Scaffold(topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "Toolbar Demo")
                            },
                            navigationIcon = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Filled.Menu, contentDescription = null)
                                }
                            },
                            actions = {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Filled.Search, contentDescription = "Search")
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Filled.Notifications, contentDescription = "Noti")
                                }
                            },

                        )
                    },
                        floatingActionButton = {
                            FloatingActionButton(onClick = { /*TODO*/ }) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon( Icons.Filled.Search, contentDescription = "Add")
                                }
                            }
                        },
                    ) {
                        it.calculateTopPadding()
//                        it.calculateLeftPadding(LayoutDirection.Ltr)}
                    Greeting2(name = "adsa")
                    }
                }
            }
        }
    }
    companion object{
        fun newIntent(context:Context){
            context.startActivity(Intent(context,ToolbarActivity::class.java))
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}
//
//@Composable
//fun ScaffoldDemo() {
//    val materialBlue700 = Color(0xFF1976D2)
//    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = { TopAppBar(title = { Text("TopAppBar") }, backgroundColor = materialBlue700) },
//        floatingActionButtonPosition = FabPosition.End,
//        floatingActionButton = {
//            FloatingActionButton(onClick = {}) {
//                Text("X")
//            }
//        },
//        drawerContent = { Text(text = "drawerContent") },
//        content = { Text("BodyContent") },
//        bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
//    )
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String = "", buttonIcon: ImageVector, onButtonClicked: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        navigationIcon = {
            IconButton(onClick = { onButtonClicked() } ) {
                Icon(buttonIcon, contentDescription = "")
            }
        },
//        backgroundColor = MaterialTheme.colors.primaryVariant
    )
}
