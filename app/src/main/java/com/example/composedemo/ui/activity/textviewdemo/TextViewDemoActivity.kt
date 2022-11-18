package com.example.composedemo.ui.activity.textviewdemo

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.OnboardingScreen
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.*

class TextViewDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                MyApp(modifier = Modifier.fillMaxWidth())
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, TextViewDemoActivity::class.java))
        }
    }
}


@Composable
private fun MyApp(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        OnboardingScreen()
    }
}


@OptIn(ExperimentalTextApi::class)
@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier
) {
    val password = remember { mutableStateOf("") }
    val GradientColors = listOf(Color.Cyan, Color.Blue, Color.Red)
    val brush = remember {
        Brush.linearGradient(
            colors = GradientColors
        )
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ellipsis Text Very long long long long long title",
            modifier = Modifier.padding(32.dp),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        RegularText("Welcome to the")
        BoldText("Compose!")
        ItalicText("Press Continue")

        Text(
            text = "Once you replace\nnegative thoughts with\npositive ones, you'll\nstart having positive\n results.",
            style = TextStyle(
                brush = brush
            )
        )
        Text(
            buildAnnotatedString {
                append("Do not allow people to dim your shine")
                append("negative thoughts with")
                withStyle(
                    SpanStyle(
                        brush = Brush.horizontalGradient(
                            colors = GradientColors
                        )
                    )
                ) {
                    append(" positive ones, you'll ")
                }
                append("start having positive ")
                append("results.")
            },
        modifier = Modifier.padding(top = 16.dp))

    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeDemoTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
