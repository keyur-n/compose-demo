package com.example.composedemo.ui.activity.constraintlayout_advance_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composedemo.ui.theme.ComposeDemoTheme

class ConstraintLayoutAdvanceDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier,
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintAdvanceDemoScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ConstraintLayoutAdvanceDemoActivity::class.java))
        }
    }
}


@Composable
private fun ConstraintAdvanceDemoScreen(modifier: Modifier = Modifier) {

    Column {
        ConstraintGuideline()
        ConstraintBarrier()
        ChainScreen()
    }

}


@Composable
private fun ConstraintGuideline() {
    androidx.compose.material.Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.padding(10.dp)

    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (username) = createRefs()
            val createGuideTop = createGuidelineFromTop(40.dp)

            Text(text = "Text With Guideline top",
                modifier = Modifier.constrainAs(username) {
                    start.linkTo(parent.start)
                    top.linkTo(createGuideTop)
                }
            )

        }
    }

}

@Composable
private fun ConstraintBarrier() {
    androidx.compose.material.Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.padding(10.dp)

    ) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (username, tech, city, barrierE) = createRefs()
            val barrierEnd = createEndBarrier(
                tech, city
            )

            Text(text = "Barrier 1",
                modifier = Modifier.constrainAs(tech) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(username.bottom, margin = 8.dp)
                }
            )
            Text(text = "Barrier 131231123",
                modifier = Modifier.constrainAs(city) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(tech.bottom, margin = 8.dp)
                }
            )
            Text(text = "Text after Largest Barrier",
                modifier = Modifier.constrainAs(barrierE) {
                    start.linkTo(barrierEnd, margin = 8.dp)
                    top.linkTo(city.bottom, margin = 8.dp)
                }
            )
        }
    }
}

@Composable
private fun ChainScreen() {
    androidx.compose.material.Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.padding(10.dp)

    ) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            val (text1, text2, text3) = createRefs()

            createHorizontalChain(
                text1, text2, text3,
                chainStyle = ChainStyle.SpreadInside
            )

            Text(text = "Text 1",
                modifier = Modifier.constrainAs(text1) {
                    start.linkTo(parent.start, margin = 8.dp)
                    top.linkTo(parent.top, margin = 8.dp)
                }
            )
            Text(text = "Text 2",
                modifier = Modifier.constrainAs(text2) {
                    start.linkTo(text1.end, margin = 8.dp)
                    top.linkTo(parent.top, margin = 8.dp)
                }
            )
            Text(text = "Text 3",
                modifier = Modifier.constrainAs(text3) {
                    start.linkTo(text2.end, margin = 8.dp)
                    top.linkTo(parent.top, margin = 8.dp)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConstraintAdvancePreview() {
    ComposeDemoTheme {
        ConstraintAdvanceDemoScreen(Modifier.fillMaxSize())
    }
}
