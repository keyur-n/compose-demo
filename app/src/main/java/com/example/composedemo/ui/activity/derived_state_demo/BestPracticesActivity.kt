package com.example.composedemo.ui.activity.derived_state_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.example.composedemo.ui.theme.ComposeDemoTheme

class BestPracticesActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                ComposeDemoTheme {
//                    DerivedStateScreen()
//                    FilterBadPracticeScreen()
                }
            }
        }
    }
    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, BestPracticesActivity::class.java))
        }
    }
}
//data class TODO(
//    val title:String,
//    val isDone:Boolean
//)
//var todos = listOf(
//    TODO("Learn Kotlin",true),
//    TODO("Learn Compose",true),
//    TODO("Practice Compose",false),
//    TODO("Apply Compose in Real Project",false),
//)
//
//@Composable
//private fun DerivedStateScreen(){
////    var a by remember { mutableStateOf(0) }
////    var b by remember { mutableStateOf(0) }
//////    val sum by remember { derivedStateOf { (a + b)>5 } }
////    val sum = remember(a,b) { mutableStateOf((a + b)>5) }
////    // Changing either a or b will cause CountDisplay to recompose but not trigger Example
////    // to recompose.
////    Column() {
////        Text(text = a.toString())
////        Text(text = b.toString())
////        Text(text = sum.toString())
////        Button(onClick = { if (a>b) b++ else a++ }) {
////            Text(text = "Increase")
////        }
////        Button(onClick = { if (a>b) a-- else b-- }) {
////            Text(text = "Decrease")
////        }
////    }
//}
//@Composable
//private fun FilterBadPracticeScreen(){
//    var todos by remember {
//        mutableStateOf(todos)
//    }
//    LazyColumn{
//        item {
//            Text(text = "Done")
//        }
//        //This will filter every time when lazy column changes
//        items(todos.filter { it.isDone }){
//            Text(text = it.title)
//        }
//        item {
//            Text(text = "UnDone")
//        }
//        //This will filter every time when lazy column changes
//        items(todos.filter { !it.isDone }){
//            Text(text = it.title)
//        }
//    }
//}
//@Composable
//private fun FilterGoodPracticeDerivedScreen(){
//    val todos by remember {
//        mutableStateOf(todos)
//    }
//
//    val doneTodosDerived by remember {
//        derivedStateOf {
//            todos.filter { it.isDone }
//        }
//    }
//    val undoneTodosDerived by remember {
//        derivedStateOf {
//            todos.filter { !it.isDone }
//        }
//    }
//    LazyColumn{
//        item {
//            Text(text = "Done")
//        }
//        items(doneTodosDerived){
//            Text(text = it.title)
//        }
//        item {
//            Text(text = "UnDone")
//        }
//        items(undoneTodosDerived){
//            Text(text = it.title)
//        }
//    }
//}
//@Composable
//private fun FilterGoodPracticeWithDerivedStateScreen(){
//    val todos by remember {
//        mutableStateOf(todos)
//    }
//    val doneTodos = remember {
//        todos.filter { it.isDone }
//    }
//    val undoneTodos = remember {
//        todos.filter { !it.isDone }
//    }
//    LazyColumn{
//        item {
//            Text(text = "Done")
//        }
//        items(doneTodos){
//            Text(text = it.title)
//        }
//        item {
//            Text(text = "UnDone")
//        }
//        items(undoneTodos){
//            Text(text = it.title)
//        }
//    }
//}
//@Preview(showBackground = true)
//@Composable
//private fun DerivedPreview(){
//    Surface {
//        DerivedStateScreen()
//    }
//}