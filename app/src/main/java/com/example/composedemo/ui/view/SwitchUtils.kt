package com.example.composedemo.ui.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun SwitchWithLabel(label: String, state: Boolean, onStateChange: (Boolean) -> Unit) {

    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                // This is for removing ripple when Row is clicked
                indication = null,
                role = Role.Switch,
                onClick = {
                    onStateChange(!state)
                }
            )
           ,
        verticalAlignment = Alignment.CenterVertically

    ) {


        Switch(
            checked = state,
            onCheckedChange = {
                onStateChange(it)
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.Magenta,
                uncheckedThumbColor = Color.Cyan,
                checkedTrackColor = Color.Green,
                uncheckedTrackColor = Color.Blue,
            ),
        )
        Spacer(modifier = Modifier.padding(start = 8.dp))
        Text(text = label)

    }
}