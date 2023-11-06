package com.example.android_gameapplication.ui.layoutComponents

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.android_gameapplication.ui.homepage.HomepageOverview

@Composable
fun StartScreen() {
    LazyColumn(
    ) {
        item {
            HomepageOverview()
        }
    }
}
