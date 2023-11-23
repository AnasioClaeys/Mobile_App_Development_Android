package com.example.android_gameapplication.ui.detailpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android_gameapplication.R
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

            Text(
                modifier = modifier
                    .padding(start = 8.dp),
                text = "Summary:",
                fontSize = 22.sp
            )

            Text(
                modifier = modifier.padding(start = 8.dp),
                text = "blaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                fontSize = 18.sp
            )

            ComponentRow("Genres", game.genres)

            ComponentRow("Platforms", game.platforms)


            Text(
                modifier = modifier.padding(8.dp),
                text = "Release date:",
                fontSize = 22.sp
            )

            FilledTonalButton(
                onClick = {
                    // Handle button click here if needed
                },
                content = {
                    Text(text = "${game.released}")
                },
                modifier = modifier.padding(start = 8.dp)
            )
        }

    }
}
