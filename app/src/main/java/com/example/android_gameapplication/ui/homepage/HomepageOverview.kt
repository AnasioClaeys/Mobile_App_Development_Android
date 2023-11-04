package com.example.android_gameapplication.ui.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_gameapplication.data.gamesList

@Composable
fun HomepageOverview() {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Popular games",
                fontSize = 28.sp
            )

            Carousel(gamesList = gamesList)

            FilledTonalButton(modifier = Modifier.padding(8.dp).width(250.dp).height(36.dp), onClick = { /*Show List Popular Games*/ },) {
                Text("More popular games", fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(8.dp),
                text = "Recently released",
                fontSize = 28.sp
            )

            Carousel(gamesList = gamesList)

            FilledTonalButton(modifier = Modifier.padding(8.dp).width(250.dp).height(36.dp), onClick = { /*Show List recently released Games*/ },) {
                Text("More recently released games", fontSize = 14.sp)
            }
        }
}


@Preview
@Composable
fun HomepageOverviewPreview() {
    HomepageOverview()
}
