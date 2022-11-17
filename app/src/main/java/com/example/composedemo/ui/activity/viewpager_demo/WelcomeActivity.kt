package com.example.composedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.activity.retrofit_demo.HomeActivity
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.RegularText
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WelcomeScreen("Android")
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, WelcomeActivity::class.java))
        }
    }
}

data class WelcomeData(
    @DrawableRes val image: Int,
    val description: String,
    val id: String,
)

val dataList = listOf<WelcomeData>(
    WelcomeData(R.drawable.ic_welcome_1, "Welcome to MTPL","home"),
    WelcomeData(R.drawable.ic_welcome_2, "Compose Journey Started","my_network"),
    WelcomeData(R.drawable.ic_welcome_1, "Viewpager compose","add_post"),
    WelcomeData(R.drawable.ic_welcome_2, "compose Image","notification"),
    WelcomeData(R.drawable.ic_welcome_1, "Compose Demo","jobs"),
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(name: String) {
    val pagerState = rememberPagerState()
    val context = LocalContext.current

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
        AnimatedVisibility(
            visible = pagerState.currentPage == pagerState.pageCount - 1,
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedButton(
                onClick = { HomeActivity.newIntent(context) },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Getting Started")
            }
        }
    }
}

@Composable
fun PageUI(welcomeData: WelcomeData) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = welcomeData.image),
            contentDescription = welcomeData.description,
//        modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        RegularText(text = welcomeData.description)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeDemoTheme {
        WelcomeScreen("Android")
    }
}