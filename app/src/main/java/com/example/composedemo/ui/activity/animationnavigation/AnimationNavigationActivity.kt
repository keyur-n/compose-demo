package com.example.composedemo.ui.activity.animationnavigation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.R
import com.google.accompanist.navigation.animation.composable
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

class AnimationNavigationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                BoxWithConstraints {
                    val width=constraints.maxWidth / 2
                    AnimationNavigationScreen(width)
                }



            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, AnimationNavigationActivity::class.java))
        }
    }
}

sealed class AnimNavigationScreen(val route: String) {
    object AnimNavigationHome : AnimNavigationScreen("first")
    object AnimNavigationSecond : AnimNavigationScreen("second")
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationNavigationScreen(width: Int) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = AnimNavigationScreen.AnimNavigationHome.route
    ) {
        composable(route = AnimNavigationScreen.AnimNavigationHome.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -width }, animationSpec = tween(
                        300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -width }, animationSpec = tween(
                        300,
                        easing = FastOutSlowInEasing
                    )
                )  + fadeIn(animationSpec = tween(300))
            }) {
            AnimHomeScreen(navController)
        }
        composable(AnimNavigationScreen.AnimNavigationSecond.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { width }, animationSpec = tween(
                        300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(300))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width }, animationSpec = tween(
                        300,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(300))
            }) {
            AnimDetailScreen()
        }
    }
}

@Composable
private fun AnimHomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.teal_700)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Screen", modifier = Modifier.padding(16.dp))
        Button(onClick = { navController.navigate(AnimNavigationScreen.AnimNavigationSecond.route) }) {
            Text(text = "Click to goto Detail")
        }
    }

}

@Composable
private fun AnimDetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Detail Screen", modifier = Modifier.padding(16.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun AnimNavigationScreenPreivew() {
    ComposeDemoTheme {
        AnimHomeScreen(rememberNavController())
    }

}

@Preview(showBackground = true)
@Composable
fun AnimNavigationScreenPreivew2() {
    ComposeDemoTheme {
        AnimDetailScreen()
    }

}