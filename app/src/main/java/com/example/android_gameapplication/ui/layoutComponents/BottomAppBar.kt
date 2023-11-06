package com.example.android_gameapplication.ui.layoutComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun BottomAppBar(onHome: () -> Unit, onSearch: () -> Unit) {
    androidx.compose.material3.BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            IconButton(onClick = onHome) {
                Icon(Icons.Outlined.Home, contentDescription = "Localized description")
            }

            IconButton(onClick = onSearch) {
                Icon(Icons.Outlined.Search, contentDescription = "Localized description")
            }
        }
    )
}
