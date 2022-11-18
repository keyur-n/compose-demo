package com.example.composedemo.ui.activity.navigation_drawer_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.R
import com.example.composedemo.TopBar
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch

class NavigationDrawerDemoActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                Surface {
                    val scaffoldState = rememberScaffoldState()
                    Scaffold(scaffoldState=scaffoldState,
                    drawerContent = {
                        Drawer()
                    }){
                        it.calculateTopPadding()
                        DrawerScreen(scaffoldState = scaffoldState)
                    }

                }
            }

        }
    }
    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, NavigationDrawerDemoActivity::class.java))
        }
    }
}

@Composable
private fun DrawerScreen(scaffoldState: ScaffoldState){
    val  scope = rememberCoroutineScope()
    IconButton(onClick = {
        scope.launch {
            scaffoldState.drawerState.open()
        }
    }) {
        Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null)

    }
}


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
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "App icon"
        )
//        screens.forEach { screen ->
//            Spacer(Modifier.height(24.dp))
//            androidx.compose.material3.Text(
//                text = screen.title,
////                style = MaterialTheme.typography.h4
//            )
//        }
        Home {

        }
        Account {

        }
        Help(navController = rememberNavController())
    }
}

@Composable
fun Home(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(
            title = "Home",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
    }
}

@Composable
fun Account(openDrawer: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(
            title = "Account",
            buttonIcon = Icons.Filled.Menu,
            onButtonClicked = { openDrawer() }
        )
    }
}
@Composable
fun Help(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        TopBar(
            title = "Help",
            buttonIcon = Icons.Filled.ArrowBack,
            onButtonClicked = { navController.popBackStack() }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DrawerScreenPreview(){
    Surface {
        DrawerScreen(rememberScaffoldState())
    }
}