package com.example.android_gameapplication.ui.layoutComponents.LandscapeComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.android_gameapplication.R

@Composable
fun NavigationRailAppBar(
    onItemSelected: (Int) -> Unit,
    isStartDestination: Boolean,
    showCloseIcon: Boolean,
    onClose: () -> Unit,
) {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        stringResource(R.string.back),
        stringResource(R.string.home_navRailBar),
        stringResource(R.string.search_NavRailBar),
    )
    val icons = listOf(
        Icons.Outlined.ArrowBack,
        Icons.Outlined.Home,
        Icons.Outlined.Search,
    )

    NavigationRail {
        if (showCloseIcon) {
            NavigationRailItem(
                icon = { Icon(Icons.Outlined.Close, contentDescription = stringResource(R.string.close_icon)) },
                label = { Text(stringResource(R.string.close_detailpage)) },
                selected = false,
                onClick = onClose,
            )
        }
        items.forEachIndexed { index, item ->
            if (index != 0 || !isStartDestination) {
                NavigationRailItem(
                    icon = { Icon(icons[index], contentDescription = item) },
                    label = { Text(item) },
                    selected = index == selectedItem,
                    onClick = {
                        selectedItem = index
                        onItemSelected(index)
                    },
                )
            }
        }
    }
}
