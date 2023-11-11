package com.example.android_gameapplication.ui.layoutComponents

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.android_gameapplication.ui.homepage.HomepageOverview

@Composable
fun StartScreen(navController: NavController, onCarousel: (Int) -> Unit) {
    LazyColumn(
    ) {
        item {
            HomepageOverview(navController = navController, onCarousel = onCarousel)
        }
    }
}
