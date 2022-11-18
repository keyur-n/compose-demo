package com.example.composedemo.ui.activity.tab_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

class TabDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TabScreen()
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, TabDemoActivity::class.java))
        }
    }
}

data class TabData(
    val tab: String,
    val des: String,
)

val tabList = mutableListOf(
    TabData("Home", "This is home page"),
    TabData("Image", "This is image page"),
    TabData("Video", "This is video page"),
)

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun TabScreen() {
    val pagerSate = rememberPagerState()
    val currentPage = pagerSate.currentPage
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        androidx.compose.material.TabRow(selectedTabIndex = currentPage,
            backgroundColor = Color.Yellow,

        ) {
            tabList.forEachIndexed { index, tabData ->
                Tab(
                    selected = currentPage == index, onClick = {
                        scope.launch {
                            pagerSate.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(text = tabData.tab)
                }
            }
        }

        HorizontalPager(
            count = tabList.size,
            state = pagerSate
        ) { index ->
            Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                Text(
                    text = tabList[index].des,

                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TabScreenPreview() {
    ComposeDemoTheme {
        TabScreen()
    }
}