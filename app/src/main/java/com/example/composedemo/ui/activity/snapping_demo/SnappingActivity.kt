package com.example.composedemo.ui.activity.snapping_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.ui.res.imageResource
import com.example.composedemo.R
import com.example.composedemo.ui.activity.shadow_demo.ShadowActivity
import com.example.composedemo.ui.activity.shadow_demo.ShadowDemoScreen
import com.example.composedemo.ui.theme.ComposeDemoTheme
import kotlinx.coroutines.launch
import kotlin.math.abs

class SnappingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme() {
                Surface {
                    SnappingDemoScreen()
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, SnappingActivity::class.java))
        }
    }
}

val dataList = mutableListOf(
    R.drawable.ic_snap_1,
    R.drawable.ic_snap_2,
    R.drawable.ic_snap_3,
    R.drawable.ic_snap_4,
    R.drawable.ic_snap_5,
    R.drawable.ic_snap_6,
    R.drawable.ic_snap_7,
    R.drawable.ic_snap_8,
    R.drawable.ic_snap_9,
    R.drawable.ic_snap_11,
)

@Composable
private fun SnappingDemoScreen() {
    val modifier = Modifier
        .height(height = Dp(150F))
        .width(width = Dp(150F))

    Surface(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center) {
            val listState = rememberLazyListState()
            val scope = rememberCoroutineScope()

            SnappingLazyRow(
                reverseLayout = false,
                userScrollEnabled = true,
                key = { index, item ->
                    //optional
                    item //Unique id
                },
                scaleCalculator = { offset, halfRowWidth ->
                    (1f - minOf(1f, abs(offset).toFloat() / halfRowWidth) * 0.45f)
                },
                items = dataList,
                itemWidth = 80.dp,
                onSelect = {

                },
                listState = listState
            ) { index, item, scale ->
                Image(
                    // first parameter of our Image widget
                    // is our image path which we have created
                    // above and name it as img.
                    painter = painterResource(item),
                    contentDescription = "Image",

                    // below line is used to give
                    // alignment to our image view.
                    alignment = Alignment.Center,

                    // below line is used to scale our image
                    // we are using center crop for it.
                    contentScale = ContentScale.Crop,

                    // below line is used to add modifier
                    // to our image. we are adding modifier
                    // which we have created above and name
                    // it as modifier.
                    modifier = modifier
                        .scale(scale)
                        .clickable {
                            scope.launch {
                                listState.animateScrollToItem(index)
                            }
                        }
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SnappingDemoScreenPreview() {
    ComposeDemoTheme {
        SnappingDemoScreen()
    }
}