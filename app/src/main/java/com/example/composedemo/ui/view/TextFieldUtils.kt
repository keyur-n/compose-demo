package com.example.composedemo.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import com.example.composedemo.R
import com.example.composedemo.ui.theme.Blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(modifier : Modifier = Modifier, password: MutableState<String>){
//    val password = remember { mutableStateOf("") }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    TextField(value = password.value, onValueChange = {
        password.value=it

    },
        leadingIcon = {
            Image(painter = painterResource(id = R.drawable.ic_password), contentDescription =null )/*Icon(painterResource(id = R.mipmap.ic_launcher), contentDescription = null)*/
        },
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, description)
            }
        },
        label = {
            Text(text = "Password")
        },
        placeholder = {
            Text(text = "Enter Password")
        },
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(128.dp),
        singleLine = true,
        modifier = modifier.padding(16.dp),
        colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Blue,
            focusedIndicatorColor =  Color.Transparent, //hide the indicator
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(modifier : Modifier = Modifier, textState: MutableState<String>){
    TextField(
        value = textState.value,
        onValueChange = {
            textState.value=it
        },
        modifier = modifier
//                .fillMaxWidth()
//                .padding(vertical = 4.dp)
        ,
        shape = RoundedCornerShape(218.dp),
        trailingIcon = {
            Icon(Icons.Filled.Add, "", tint = Blue)
        },
        colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Blue,
            focusedIndicatorColor =  Color.Blue, //hide the indicator
            unfocusedIndicatorColor = Color.Blue,
            containerColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsernameTextField(modifier : Modifier = Modifier, textState: MutableState<String>){
    TextField(
        value = textState.value,
        onValueChange = {
            textState.value=it
        },
        modifier = modifier
//                .fillMaxWidth()
//                .padding(vertical = 4.dp)
        ,
        shape = RoundedCornerShape(218.dp),
        trailingIcon = {
            Icon(Icons.Filled.Add, "", tint = Blue)
        },
        colors = TextFieldDefaults.textFieldColors(
//                backgroundColor = Color.Blue,
            focusedIndicatorColor =  Color.Blue, //hide the indicator
            unfocusedIndicatorColor = Color.Blue,
            containerColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
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
}