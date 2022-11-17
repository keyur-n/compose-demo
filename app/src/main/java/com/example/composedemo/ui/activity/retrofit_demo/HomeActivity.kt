package com.example.composedemo.ui.activity.retrofit_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.composedemo.DrawerActivity
import com.example.composedemo.model.Entry
import com.example.composedemo.network.ApiState
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.view.BoldText
import com.example.composedemo.ui.view.RegularText
import com.example.composedemo.viewmodel.HomeViewModel

class HomeActivity : ComponentActivity() {
    val homeViewModel: HomeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GetEntryData(homeViewModel)
                    homeViewModel.callApiEntry()
                }
            }
        }
    }

    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, HomeActivity::class.java))
        }
    }
}

@Composable
fun GetEntryData(homeViewModel: HomeViewModel) {

    when (val result = homeViewModel.listEntry.value) {
        ApiState.Empty -> {

        }
        is ApiState.Failure -> {
            Toast.makeText(LocalContext.current, result.message, Toast.LENGTH_LONG).show()
        }
        ApiState.Loading -> {
            Column(
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }

        }
        is ApiState.Success -> {
            LazyColumn {
                items(items = result.list.toList()) { name ->
                    EntryRow(name)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryRow(entry: Entry) {
    val context=LocalContext.current
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = {
            DrawerActivity.newIntent(context = context)
        }
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            BoldText(text = entry.category)
            RegularText(text = entry.description)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeDemoTheme {
        GetEntryData(homeViewModel = HomeViewModel())
    }
}