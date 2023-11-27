package com.example.android_gameapplication.ui.layoutComponents

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.ui.homepage.HomepageOverview

@Composable
fun StartScreen(
    onCarousel: (Int) -> Unit,
    onListPopularGamesAllTime: () -> Unit,
    onListPopularGamesOfThisYear: () -> Unit
) {
    LazyColumn(
    ) {
        item {
            HomepageOverview(
                onCarousel = onCarousel,
                onListPopularGamesAllTime = onListPopularGamesAllTime,
                onListPopularGamesOfThisYear = onListPopularGamesOfThisYear
            )
        }
    }
}
