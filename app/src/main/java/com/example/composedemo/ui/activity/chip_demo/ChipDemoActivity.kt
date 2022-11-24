package com.example.composedemo.ui.activity.chip_demo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.theme.ComposeDemoTheme
import com.example.composedemo.ui.theme.Purple80
import com.example.composedemo.ui.theme.PurpleGrey40

class ChipDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface {
                ChipsScreen()
            }
        }
    }
    companion object {
        fun newIntent(context: Context) {
            context.startActivity(Intent(context, ChipDemoActivity::class.java))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChipsScreen(modifier: Modifier=Modifier.padding(top = 16.dp)) {
//    val chipsList by remember { mutableStateOf(listOf("India", "New Zealand", "Australia")) }
    val chipsList = remember { mutableStateListOf<String>("India", "New Zealand", "Australia") }
    var chipState by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Suggestion Chip", modifier = modifier)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            chipsList.forEachIndexed { index, s ->
                SuggestionChipItem(label = s, selected = chipState.equals(s), onChipChange = {
                    chipState = it
                })
            }
        }
        Text(text = "Assist Chip", modifier = modifier)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            chipsList.forEachIndexed { index, s ->
                AssistChipItem(label = s, selected = chipState.equals(s), onChipChange = {
                    chipState = it
                })
            }
        }
        Text(text = "Filter Chip", modifier = modifier)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            chipsList.forEachIndexed { index, s ->
                FilterChipItem(label = s, selected = chipState.equals(s), onChipChange = {
                    chipState = it
                })
            }
        }
        Text(text = "Elevated Filtered Chip", modifier = modifier)
        ElevatedFilteredChipItem()
        Text(text = "Input Chip Chip - Horizontal scrollable", modifier = modifier)

        InputChipItem()
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            chipsList.forEachIndexed { index, s ->
                InputChipItem2(label = s, selected = chipState.equals(s), onChipChange = {
                    chipState = it
                }, onCancel = {
                    chipsList.removeAll {
                        it == s
                    }
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SuggestionChipItem(
    label: String, selected: Boolean,
    onChipChange: (String) -> Unit
) {
    SuggestionChip(onClick = {
        if (!selected)
            onChipChange(label)
        else
            onChipChange("")
    }, label = {
        Text(text = label)
    }, modifier = Modifier.padding(horizontal = 10.dp),
        shape = RoundedCornerShape(8.dp),
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor
            = if (selected) Purple80 else Color.Transparent
        ),
        border = SuggestionChipDefaults.suggestionChipBorder(
            borderColor =
            if (selected) Color.Transparent else PurpleGrey40
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AssistChipItem(
    label: String, selected: Boolean,
    onChipChange: (String) -> Unit
) {
    AssistChip(onClick = {
        if (!selected)
            onChipChange(label)
        else
            onChipChange("")
    }, label = {
        Text(text = label)
    }, modifier = Modifier.padding(horizontal = 10.dp),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            Icon(
                Icons.Filled.Settings,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FilterChipItem(
    label: String, selected: Boolean,
    onChipChange: (String) -> Unit
) {
    FilterChip(onClick = {
        if (!selected)
            onChipChange(label)
        else
            onChipChange("")
    }, label = {
        Text(text = label)
    }, modifier = Modifier.padding(horizontal = 10.dp),
        shape = RoundedCornerShape(8.dp),
        colors = FilterChipDefaults.filterChipColors(
            containerColor
            = if (selected) Purple80 else Color.Transparent
        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor =
            if (selected) Color.Transparent else PurpleGrey40
        ),
        selected = selected
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ElevatedFilteredChipItem(){
    var selected by remember { mutableStateOf(false) }
    ElevatedFilterChip(
        selected = selected,
        onClick = { selected = !selected },
        label = { Text("Filter chip") },
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Localized Description",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputChipItem(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            repeat(9) { index ->
                var selectedState by remember { mutableStateOf(false) }
                InputChip(
                    modifier = Modifier.padding(start = 8.dp),
                    selected = selectedState,
                    onClick = { selectedState = !selectedState },
                    label = { Text("Input Chip") },
                    avatar = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = "Localized description",
                            Modifier.size(InputChipDefaults.AvatarSize)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Cancel,
                            contentDescription = "Localized description",
                            Modifier.size(AssistChipDefaults.IconSize).clickable {

                            }
                        )
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InputChipItem2(  label: String, selected: Boolean,
                             onChipChange: (String) -> Unit,onCancel:() -> Unit){

    InputChip(
        modifier = Modifier.padding(start = 8.dp),
        selected = selected,
        onClick = { onChipChange(label) },
        label = { Text(label) },
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Localized description",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Cancel,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize).clickable {
                    onCancel()
                }
            )
        }
    )
}
@Preview(showBackground = true)
@Composable
private fun ChipsScreenPreview() {
    ComposeDemoTheme {
        ChipsScreen()
    }
}