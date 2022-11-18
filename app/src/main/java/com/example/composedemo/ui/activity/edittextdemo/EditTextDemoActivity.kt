package com.example.composedemo.ui.activity.edittextdemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.DefaultTextField
import com.example.composedemo.ui.view.PasswordTextField

class EditTextDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EditTextScreen()
                }
            }
        }
    }
    companion object{
        fun newIntent(context: Context){
            context.startActivity(Intent(context,EditTextDemoActivity::class.java))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EditTextScreen(modifier: Modifier = Modifier) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context=LocalContext.current
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to ")
        Text(text = "MTPL")
        OutlinedTextField(value = username.value, onValueChange = {
            username.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },
            label = {
                Text(text = "Username")
            },
            placeholder = {
                Text(text = "Enter username")
            }
        )
        TextField(
            modifier=Modifier.padding(8.dp),
            value = username.value, onValueChange = {
            username.value = it
        },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },
            label = {
                Text(text = "Username")
            },
            placeholder = {
                Text(text = "Enter username")
            }
        )
        OutlinedTextField(
            value = password.value, onValueChange = {
                password.value = it

            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },
            label = {
                Text(text = "Password")
            },
            placeholder = {
                Text(text = "Enter Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Password
            ),
            shape = RoundedCornerShape(8.dp),
        )
        PasswordTextField(password = password)
        DefaultTextField(textState = password)
        OutlinedTextField(
            value = password.value, onValueChange = {
                password.value = it

            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },
            label = {
                Text(text = "Password")
            },
            placeholder = {
                Text(text = "Enter Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.None,
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Password
            ),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Yellow,
                focusedIndicatorColor = Color.Green
            )
        )
    }

}


@Preview(showBackground = true)
@Composable
fun EditTextScreenPreview() {
    ComposeDemoTheme {
        EditTextScreen()
    }
}