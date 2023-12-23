package com.example.android_gameapplication.ui.layoutComponents

import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

/**
 * A Composable function that creates a top app bar.
 * This app bar is used across different screens of the application and includes a title and a navigation icon.
 *
 * @param navigationIcon A composable lambda function that defines the navigation icon to be displayed on the top app bar.
 * @param title The resource ID of the title string to be displayed in the top app bar.
 */
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopAppBar(
    navigationIcon: @Composable () -> Unit,
    @StringRes title: Int,
) {
    androidx.compose.material3.TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = stringResource(title),
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = navigationIcon,
    )
}
