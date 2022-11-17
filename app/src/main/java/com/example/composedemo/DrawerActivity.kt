package com.example.composedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.composedemo.ui.theme.ComposeDemoTheme

class DrawerActivity : ComponentActivity() {
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
                                Text(text = "Drawer Demo")
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
                        }
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
            context.startActivity(Intent(context,DrawerActivity::class.java))
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

sealed class DrawerScreens(val title: String, val route: String) {
    object Home : DrawerScreens("Home", "home")
    object Account : DrawerScreens("Account", "account")
    object Help : DrawerScreens( "Help", "help")
}
private val screens = listOf(
    DrawerScreens.Home,
    DrawerScreens.Account,
    DrawerScreens.Help
)
@Composable
fun Drawer(
    modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "App icon"
        )
        screens.forEach { screen ->
            Spacer(Modifier.height(24.dp))
            Text(
                text = screen.title,
//                style = MaterialTheme.typography.h4
            )
        }
    }
}
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
@Composable
fun Home(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Home",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Home Page content here.")
        }
    }
}

@Composable
fun Account(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Account",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Account."
//                , style = MaterialTheme.typography.h4
            )
        }
    }
}
@Composable
fun Help(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            title = "Help",
            buttonIcon = Icons.Filled.ArrowBack,
            onButtonClicked = { navController.popBackStack() }
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
//            Text(text = "Help.", style = MaterialTheme.typography.h4)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeDemoTheme {
        Drawer()
    }
}