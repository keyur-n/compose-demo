package com.example.composedemo.ui.activity.constraint_set_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.composedemo.R
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.DefaultTextField
import com.example.composedemo.ui.view.PasswordTextField
import com.example.composedemo.ui.view.RegularButton
import com.example.composedemo.ui.view.UsernameTextField

class ConstraintSetDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
//                    modifier = Modifier,
//                    color = MaterialTheme.colorScheme.background
                ) {
                    ConstraintSetScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ConstraintSetDemoActivity::class.java))
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ConstraintSetScreen(modifier: Modifier = Modifier) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val constraint = ConstraintSet {
        val etUsername = createRefFor("username")
        val etPassword = createRefFor("password")
        val btLogin = createRefFor("login")
        constrain(etUsername) {
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        }
        constrain(etPassword) {
            start.linkTo(parent.start)
            top.linkTo(etUsername.bottom, margin = 16.dp)
        }
        constrain(btLogin) {
            start.linkTo(parent.start)
            top.linkTo(etPassword.bottom, margin = 16.dp)
            end.linkTo(etPassword.end)
        }
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        ConstraintLayout(
            constraintSet = constraint
        ) {
            UsernameTextField(
                modifier = Modifier
                    .layoutId("username"), textState = username
            )
            PasswordTextField(
                modifier = Modifier
                    .layoutId("password"), password = password
            )

            Button(
                onClick = {  },
                modifier = Modifier
                    .layoutId("login"),
                ) {
                Text(text = "Login")
            }
        }
    }

}


@Preview(showBackground = true, widthDp = 320)
@Composable
private fun ConstraintSetPreview() {
    ComposeDemoTheme {
        Surface(
//                    modifier = Modifier,
//                    color = MaterialTheme.colorScheme.background
        ) {
            ConstraintSetScreen(modifier = Modifier.fillMaxSize())
        }
    }
}
