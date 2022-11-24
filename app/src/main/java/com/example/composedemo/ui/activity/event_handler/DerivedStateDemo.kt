package com.example.composedemo.ui.activity.event_handler

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme

data class TODO(
    val title: String,
    val isDone: Boolean
)

var todos = listOf(
    TODO("Learn Kotlin", true),
    TODO("Learn Compose", true),
    TODO("Practice Compose", false),
    TODO("Apply Compose in Real Project", false),
)

@Composable
fun DerivedStateScreen() {
    ComposeDemoTheme {
        Column {
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.White,
                modifier = Modifier.padding(10.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Bad Practice Demo - Look into the code to understand")
                    FilterBadPracticeScreen()
                }
            }
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.White,
                modifier = Modifier.padding(10.dp)
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = "Derived State Demo - Look into the code to understand")
                    FilterGoodPracticeDerivedScreen()
                }
            }

        }

    }
}

@Composable
fun FilterBadPracticeScreen() {
    var todos by remember {
        mutableStateOf(todos)
    }
    LazyColumn {
        item {
            Text(text = "Done")
        }
        //This will filter every time when lazy column changes
        items(todos.filter { it.isDone }) {
            Text(text = it.title)
        }
        item {
            Text(text = "UnDone")
        }
        //This will filter every time when lazy column changes
        items(todos.filter { !it.isDone }) {
            Text(text = it.title)
        }
    }
}

@Composable
private fun FilterGoodPracticeDerivedScreen() {
    val todos by remember {
        mutableStateOf(todos)
    }

    val doneTodosDerived by remember {
        derivedStateOf {
            todos.filter { it.isDone }
        }
    }
    val undoneTodosDerived by remember {
        derivedStateOf {
            todos.filter { !it.isDone }
        }
    }
    LazyColumn {
        item {
            Text(text = "Done")
        }
        items(doneTodosDerived) {
            Text(text = it.title)
        }
        item {
            Text(text = "UnDone")
        }
        items(undoneTodosDerived) {
            Text(text = it.title)
        }
    }
}

@Composable
private fun FilterGoodPracticeWithDerivedStateScreen() {
    val todos by remember {
        mutableStateOf(todos)
    }
    val doneTodos = remember {
        todos.filter { it.isDone }
    }
    val undoneTodos = remember {
        todos.filter { !it.isDone }
    }
    LazyColumn {
        item {
            Text(text = "Done")
        }
        items(doneTodos) {
            Text(text = it.title)
        }
        item {
            Text(text = "UnDone")
        }
        items(undoneTodos) {
            Text(text = it.title)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DerivedPreview() {
    Surface {
        DerivedStateScreen()
    }
}