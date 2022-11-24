package com.example.composedemo.ui.activity.deeplink_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.composedemo.R
import com.example.composedemo.ui.activity.bottombar.*
import com.example.composedemo.ui.activity.chip_demo.ChipDemoActivity
import com.example.composedemo.ui.theme.ComposeDemoTheme

class DeepLinkDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DeepLinkScreen(navController = rememberNavController())
        }

    }
    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, DeepLinkDemoActivity::class.java))
        }
    }
}


sealed class DeepLinkRouteItem(var title: String, var icon: Int, var screen_route: String) {
    object Home : DeepLinkRouteItem("Home", R.drawable.ic_home, "home")
    object DeepLink : DeepLinkRouteItem("DeepLink", R.drawable.ic_my_network, "deeplink/{id}")
    {
        fun createRoute(id:Int)="deeplink/${id}"
    }
}

@Composable
private fun DeepLinkScreen(navController: NavHostController) {
    NavHost(navController, startDestination = DeepLinkRouteItem.Home.screen_route) {
        composable(DeepLinkRouteItem.Home.screen_route) {
            Button(onClick = {
                navController.navigate(DeepLinkRouteItem.DeepLink.createRoute(-1))
            }) {
                Text(
                    text = "Home Screen",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }

        }
        composable(DeepLinkRouteItem.DeepLink.screen_route,
            deepLinks = listOf(navDeepLink {
                this.uriPattern = "https://pl-coding.com/{id}"
                this.action = Intent.ACTION_VIEW
            }
            ),
            arguments= listOf(navArgument("id"){
                type=NavType.IntType
                defaultValue=-1
            })
        ) {entry->
            val id=entry.arguments?.getInt("id")
            DeepLinkUISCreen(id!!)
        }

    }
}

@Composable
private fun DeepLinkUISCreen(id:Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "My Network Id= $id",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DeepLinkPreview() {
    ComposeDemoTheme {

    }
}