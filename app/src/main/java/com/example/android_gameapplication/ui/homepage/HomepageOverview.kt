package com.example.android_gameapplication.ui.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_gameapplication.data.gamesList

@Composable
fun HomepageOverview() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            MainComponentHomepage(title = "Popular games", gamesList= gamesList)

            Spacer(modifier = Modifier.height(8.dp))

            MainComponentHomepage(title = "Recently released", gamesList = gamesList)

        }
}


@Preview
@Composable
fun HomepageOverviewPreview() {
    HomepageOverview()
}
