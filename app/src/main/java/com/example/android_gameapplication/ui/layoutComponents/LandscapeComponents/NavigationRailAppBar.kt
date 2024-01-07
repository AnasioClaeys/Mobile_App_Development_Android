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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.example.android_gameapplication.R

/**
 * A Composable function that creates a navigation rail app bar for landscape mode.
 * It displays navigation items with optional back and close icons.
 *
 * @param onItemSelected A lambda function to be invoked when a navigation item is selected, passing the item's index.
 * @param isStartDestination Boolean to indicate if the current destination is the start destination, affecting the visibility of the back icon.
 * @param showCloseIcon Boolean to control the visibility of the close icon.
 * @param onClose A lambda function to be invoked when the close icon is clicked.
 */
@Composable
fun NavigationRailAppBar(
    onItemSelected: (Int) -> Unit,
    isStartDestination: Boolean,
    showCloseIcon: Boolean,
    onClose: () -> Unit,
) {
    var selectedItem by remember { mutableIntStateOf(0) }
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
                icon = {
                    Icon(
                        Icons.Outlined.Close,
                        contentDescription = stringResource(R.string.close_icon)
                    )
                },
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
