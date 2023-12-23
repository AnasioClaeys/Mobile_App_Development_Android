package com.example.android_gameapplication.ui.layoutComponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.android_gameapplication.R

/**
 * A Composable function that creates a bottom app bar.
 * It includes navigation items for home and search, with visual and functional elements based on the current navigation state.
 *
 * @param onHome A lambda function to be invoked when the home item is clicked.
 * @param onSearch A lambda function to be invoked when the search item is clicked.
 * @param currentBackStackEntry The current back stack entry name to determine which item is selected.
 */
@Composable
fun BottomAppBar(onHome: () -> Unit, onSearch: () -> Unit, currentBackStackEntry: String?) {
    androidx.compose.material3.BottomAppBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary,
        actions = {
            NavigationBarItem(
                selected = currentBackStackEntry == Destinations.Start.name,
                onClick = onHome,
                icon = {
                    Icon(
                        Icons.Outlined.Home,
                        contentDescription = stringResource(R.string.bottomAppBar_navigate_to_home_screen),
                    )
                },
                label = { Text(text = stringResource(R.string.home)) },
            )

            NavigationBarItem(
                selected = currentBackStackEntry == Destinations.Search.name,
                onClick = onSearch,
                icon = {
                    Icon(
                        Icons.Outlined.Search,
                        contentDescription = stringResource(R.string.bottomAppBar_navigate_to_search_screen),
                    )
                },
                label = { Text(text = stringResource(R.string.search)) },
            )
        },
    )
}
