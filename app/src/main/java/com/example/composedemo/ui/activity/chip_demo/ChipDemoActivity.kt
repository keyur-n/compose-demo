package com.example.composedemo.ui.activity.chip_demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
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

            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChipsScreen() {
    val chipsList by remember { mutableStateOf(listOf("India", "New Zealand", "Australia")) }
    var chipState by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            chipsList.forEachIndexed { index, s ->
                ChipItem(label = s, selected = chipState.equals(s), onChipChange = {
                    chipState = it
                })
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            chipsList.forEachIndexed { index, s ->
                ChipItem2(label = s, selected = chipState.equals(s), onChipChange = {
                    chipState = it
                })
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            chipsList.forEachIndexed { index, s ->
                ChipItem3(label = s, selected = chipState.equals(s), onChipChange = {
                    chipState = it
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChipItem(
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
private fun ChipItem2(
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
private fun ChipItem3(
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
@Preview(showBackground = true)
@Composable
private fun ChipsPreview() {
    ComposeDemoTheme {
        ChipsScreen()
    }
}
