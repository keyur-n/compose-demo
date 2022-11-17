package com.example.composedemo.ui.activity.bottombar_advance

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composedemo.PageUI
import com.example.composedemo.R
import com.example.composedemo.dataList
import com.example.composedemo.ui.activity.bottombar.*
import com.example.composedemo.ui.activity.retrofit_demo.HomeActivity
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.ic_home,"home")
    object MyNetwork: BottomNavItem("My Network",R.drawable.ic_my_network,"my_network")
    object AddPost: BottomNavItem("Post",R.drawable.ic_post,"add_post")
    object Notification: BottomNavItem("Notification",R.drawable.ic_notification,"notification")
    object Jobs: BottomNavItem("Jobs",R.drawable.ic_job,"jobs")
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    val pagerState = rememberPagerState()

    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.MyNetwork.screen_route) {
            NetworkScreen()
        }
        composable(BottomNavItem.AddPost.screen_route) {
            AddPostScreen()
        }
        composable(BottomNavItem.Notification.screen_route) {
            NotificationScreen()
        }
        composable(BottomNavItem.Jobs.screen_route) {
            JobScreen()
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigation() {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyNetwork,
        BottomNavItem.AddPost,
        BottomNavItem.Notification,
        BottomNavItem.Jobs
    )
    val pagerState = rememberPagerState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column {

        HorizontalPager(
            count = dataList.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            PageUI(welcomeData = dataList[page])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp),
            activeColor = colorResource(id = R.color.purple_500)
        )

        androidx.compose.material.BottomNavigation(
            backgroundColor = colorResource(id = R.color.teal_200),
            contentColor = Color.Black,

            ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                    label = { Text(text = item.title,
                        fontSize = 9.sp) },
                    selectedContentColor = Color.Red,
                    unselectedContentColor = Color.Black.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = dataList[pagerState.currentPage].id == item.screen_route,
                    onClick = {
                        when(item){
                            BottomNavItem.AddPost -> {
                                scope.launch {
                                    pagerState.animateScrollToPage(2,)
                                }
                            }
                            BottomNavItem.Home -> {
                                scope.launch {
                                    pagerState.animateScrollToPage(0,)
                                }
                            }
                            BottomNavItem.Jobs -> {
                                scope.launch {
                                    pagerState.animateScrollToPage(4,)
                                }
                            }
                            BottomNavItem.MyNetwork -> {
                                scope.launch {
                                    pagerState.animateScrollToPage(1,)
                                }
                            }
                            BottomNavItem.Notification -> {
                                scope.launch {
                                    pagerState.animateScrollToPage(3,)
                                }
                            }
                        }

                    }
                )
            }
        }
    }

}