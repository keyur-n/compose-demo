package com.example.composedemo.ui.activity.shadow_demo

import android.content.Context
import android.content.Intent
import android.graphics.BlurMaskFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class ShadowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme() {
                Surface {
                    ShadowDemoScreen()
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ShadowActivity::class.java))
        }
    }
}

@Composable
fun ShadowDemoScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, color = Color.Black), shape = RoundedCornerShape(50.dp))
                .fillMaxWidth()
                .shadow(
                    color = Color.Green,
                    borderRadius = 50.dp,
                    blurRadius = 8.dp,
                )
                .height(50.dp)
                .clip(RoundedCornerShape(50.dp))
                .background(color = Color.White)
        ) {

        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, color = Color.Black))
                .shadow(
                    color = Color.Red,
                    blurRadius = 8.dp,
                )
                .background(color = Color.White)
                .fillMaxWidth()
                .height(50.dp)
        ) {

        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, color = Color.Black))
                .shadow(
                    color = Color.Magenta,
                    blurRadius = 8.dp,
                    spread = 8.dp
                )
                .background(color = Color.White)
                .fillMaxWidth()
                .height(50.dp)
        ) {

        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, color = Color.Black))
                .shadow(
                    color = Color.Blue,
                    blurRadius = 6.dp,

                    )
                .background(color = Color.White)
                .fillMaxWidth()
                .height(50.dp)
        ) {

        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, color = Color.Black))
                .shadow(
                    color = Color.Magenta,
                    blurRadius = 8.dp,
                    offsetY = 16.dp,
                    offsetX = 8.dp,
                )
                .background(color = Color.White)
                .fillMaxWidth()
                .height(50.dp)
        ) {

        }
        Spacer(modifier = Modifier.height(28.dp))
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, color = Color.Black))
                .shadow(
                    color = Color.Magenta,
                    blurRadius = 8.dp,
                    offsetY = (-4).dp,
                    offsetX = (-4).dp,
                )
                .background(color = Color.White)
                .fillMaxWidth()
                .height(50.dp)
        ) {

        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, color = Color.Black))
                .shadow(
                    color = Color.Green,
                    blurRadius = 8.dp,
                    offsetY = (25).dp,
//                    offsetX = (-4).dp,
                )
                .background(color = Color.White)
                .fillMaxWidth()
                .height(50.dp)
        ) {

        }
        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .border(BorderStroke(1.dp, color = Color.Black))
                .shadow(
                    color = Color.Cyan,
                    blurRadius = 8.dp,
                    offsetX = (100).dp,
                )
                .background(color = Color.White)
                .fillMaxWidth()
                .height(50.dp)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShadowDemoScreenPreview() {

    ComposeDemoTheme() {
        Surface {
            ShadowDemoScreen()
        }
    }

}

fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    blurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    spread: Dp = 0f.dp,
    modifier: Modifier = Modifier
) = this.then(
    modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = (this.size.width + spreadPixel)
            val bottomPixel = (this.size.height + spreadPixel)

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)