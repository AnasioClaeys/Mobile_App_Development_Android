package com.example.android_gameapplication.ui.detailpage

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ComponentRow(title: String, component: List<String>, modifier:Modifier= Modifier) {
    Text(
        modifier = modifier.padding(8.dp),
        text = "${title}:",
        fontSize = 22.sp
    )

    LazyRow(
        content = {
            component.forEach { component ->
                item {
                    FilledTonalButton(
                        onClick = {
                            // Handle button click here if needed
                        },
                        content = {
                            Text(text = component)
                        },
                        modifier = modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    )
}
