package com.example.android_gameapplication.ui.layoutComponents

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.android_gameapplication.ui.homepage.HomepageOverview

/**
 * A Composable function that serves as the start screen for the application.
 * It displays the homepage overview within a scrollable column.
 *
 * @param onCarousel A lambda function to be invoked when an item in the carousel is selected, passing the game's ID.
 * @param onListPopularGamesAllTime A lambda function to be invoked when the 'more games of all time' button is clicked.
 * @param onListPopularGamesOfThisYear A lambda function to be invoked when the 'more games of this year' button is clicked.
 */
@Composable
fun StartScreen(
    onCarousel: (Int) -> Unit,
    onListPopularGamesAllTime: () -> Unit,
    onListPopularGamesOfThisYear: () -> Unit,
) {
    LazyColumn() {
        item {
            HomepageOverview(
                onCarousel = onCarousel,
                onListPopularGamesAllTime = onListPopularGamesAllTime,
                onListPopularGamesOfThisYear = onListPopularGamesOfThisYear,
            )
        }
    }
}
