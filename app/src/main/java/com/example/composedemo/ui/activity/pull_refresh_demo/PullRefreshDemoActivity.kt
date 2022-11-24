package com.example.composedemo.ui.activity.pull_refresh_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composedemo.ui.activity.pagination_demo.PaginationActivity
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PullRefreshDemoActivity:ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                val refreshScope = rememberCoroutineScope()
                var refreshing by remember { mutableStateOf(false) }
                var itemCount by remember { mutableStateOf(15) }
                fun refresh() = refreshScope.launch {
                    refreshing = true
                    itemCount+=5
                    delay(1500)
                    refreshing = false
                }
                val stateRefresh = rememberPullRefreshState(refreshing, ::refresh)

                Box(Modifier.pullRefresh(stateRefresh)) {
                    LazyColumn(Modifier.fillMaxSize()) {
                        if (!refreshing) {
                            items(itemCount) {
                                ListItem { Text(text = "Item ${itemCount - it}") }
                            }
                        }
                    }

                    PullRefreshIndicator(refreshing, stateRefresh, Modifier.align(Alignment.TopCenter))
                }
            }
        }
    }
    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, PullRefreshDemoActivity::class.java))
        }
    }
}