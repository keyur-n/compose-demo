package com.example.composedemo.ui.activity.bottombar_advance

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composedemo.PageUI
import com.example.composedemo.R
import com.example.composedemo.dataList
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class BottombarDemAdvanceActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            BottomNavigation(navController = nav)
            ComposeDemoTheme() {
                MainScreenView()
            }

        }
    }

    companion object{
        fun newIntent(context: Context){
            context.startActivity(Intent(context,BottombarDemAdvanceActivity::class.java))
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreenView() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val context = LocalContext.current
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.MyNetwork,
        BottomNavItem.AddPost,
        BottomNavItem.Notification,
        BottomNavItem.Jobs
    )

    Scaffold(
        bottomBar = {
//            BottomAppBar(content = {
//                for (item in items){
//                    BottomNavigationItem(
//                        icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
//                        label = { Text(text = item.title,
//                            fontSize = 9.sp) },
//                        selectedContentColor = Color.Black,
//                        unselectedContentColor = Color.Black.copy(0.4f),
//                        alwaysShowLabel = true,
//                        selected = currentRoute == item.screen_route,
//                        onClick = {
//                            scope.launch {  }
//                            when(item){
//                                BottomNavItem.AddPost -> {
//                                    scope.launch {
//                                        pagerState.animateScrollToPage(2,)
//                                    }
//                                }
//                                BottomNavItem.Home -> {
//                                    scope.launch {
//                                        pagerState.animateScrollToPage(0,)
//                                    }
//                                }
//                                BottomNavItem.Jobs -> {
//                                    scope.launch {
//                                        pagerState.animateScrollToPage(1,)
//                                    }
//                                }
//                                BottomNavItem.MyNetwork -> {
//                                    scope.launch {
//                                        pagerState.animateScrollToPage(1,)
//                                    }
//                                }
//                                BottomNavItem.Notification -> {
//                                    scope.launch {
//                                        pagerState.animateScrollToPage(0,)
//                                    }
//
//
//
//                                }
//                            }
//
//
////                            navController.navigate(item.screen_route) {
////
////                                navController.graph.startDestinationRoute?.let { screen_route ->
////                                    popUpTo(screen_route) {
////                                        saveState = true
////                                    }
////                                }
////                                launchSingleTop = true
////                                restoreState = true
////                            }
//                        }
//                    )
//                }
//            },)
            BottomNavigation()
        }
    ) {
        it.calculateTopPadding()
//        HorizontalPager(
//            count = dataList.size,
//            state = pagerState,
//            modifier = Modifier
//                .fillMaxWidth()
////                .weight(1f)
//        ) { page ->
//            PageUI(welcomeData = dataList[page])
//        }
//        com.example.composedemo.ui.activity.bottombar.NavigationGraph(navController = navController)
    }
}