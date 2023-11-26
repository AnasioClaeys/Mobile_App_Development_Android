package com.example.android_gameapplication.ui.detailpage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android_gameapplication.model.Game

@Composable
fun Detailpage(game: Game, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        item {
            AsyncImage(
                model = game.backgroundImage, contentDescription = game.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )


            Text(
                modifier = modifier.padding(8.dp),
                text = "${game.name}",
                fontSize = 28.sp
            )


            ComponentRow("Genres", game.genres)

            ComponentRow("Platforms", game.platforms)

            ComponentRow(title = "Release date:", component = listOf("${game.released}"))

            ComponentRow(title = "Average Playtime", component = listOf("${game.playtime} hours"))
        }

    }
}
