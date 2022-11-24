package com.example.composedemo.ui.activity.animation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class AnimationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            BottomNavigation(navController = nav)
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                androidx.compose.material3.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AnimationScreen()
                }
            }

        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, AnimationActivity::class.java))
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationScreen() {


    Column(modifier = Modifier.fillMaxSize()) {
        var isVisible by remember { mutableStateOf(false) }
        var isRound by remember { mutableStateOf(false) }
        Button(onClick = {
            isVisible = !isVisible
            isRound = !isRound
        }) {
            Text(text = "Toggle")
        }
        Row {
            val borderRadiusTween by animateIntAsState(
                targetValue = if (isRound) 100 else 0,
                animationSpec = tween(
                    durationMillis = 2000, delayMillis = 500,
                    easing = LinearEasing
                )
            )

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(borderRadiusTween))
                    .background(color = Color.Blue)
            )
            Spacer(modifier = Modifier.width(8.dp))

            val borderRadiusSpring by animateIntAsState(
                targetValue = if (isRound) 40 else 20,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            )

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(borderRadiusSpring))
                    .background(color = Color.Blue)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row() {
            val transition = updateTransition(targetState = isRound, label = null)
            val borderRadiusTweenTransition by transition.animateInt(transitionSpec = { tween(2000) },
                label = "BorderRadius",
                targetValueByState = {isRoundR->
                    if (isRoundR) 100 else 0
                })
            val colorTransition by transition.animateColor(transitionSpec = { tween(1000)},
                label = "Color",
                targetValueByState = {isColor->
                    if (isColor) Color.Green else Color.Red
                })

            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(borderRadiusTweenTransition))
                    .background(color = colorTransition)
            )

            Spacer(modifier = Modifier.width(8.dp))
            val transitionRe= rememberInfiniteTransition()
            val colorAnim by transitionRe.animateColor(initialValue = Color.Red,
                targetValue = Color.Green,
                animationSpec = infiniteRepeatable(
                    animation = tween(2000),
                    repeatMode = RepeatMode.Reverse
                ))
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(color = colorAnim)
            ){
                Text(text = "Infinite Color Animation")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row() {
            AnimatedContent(targetState = isVisible,
            modifier = Modifier.fillMaxWidth().weight(1f),
            content = { isVisibleContent->
                if (isVisibleContent){
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(color = Color.Green)
                    )
                }else{
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .background(color = Color.Red)
                    )
                }
            },
            transitionSpec = {
                slideInHorizontally(initialOffsetX = {
                   if (isVisible) it else -it
                }) with slideOutHorizontally(targetOffsetX = {
                    if (isVisible) -it else it
                })
            })
        }


        AnimatedVisibility(visible = isVisible,
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutHorizontally() + fadeOut()
        ) {
            Box(modifier = Modifier.background(color = Color.Red)) {
                Text(text = "I'm Animated")
            }
        }

    }

}


@Preview(showBackground = true)
@Composable
private fun AnimationScreenPreview() {
    ComposeDemoTheme {
        AnimationScreen()
    }
}
