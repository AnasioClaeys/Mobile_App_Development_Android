package com.example.android_gameapplication.ui.detailpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_gameapplication.R
import com.example.android_gameapplication.data.gamesList
import com.example.android_gameapplication.ui.model.Game

@Composable
fun DetailpageOverview(game: Game) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.f2dtyp_wwaabdst),
            contentDescription = game.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = "${game.title}",
            fontSize = 28.sp
        )

        Text(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .padding(start = 8.dp),
            text = "Summary:",
            fontSize = 22.sp
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "blaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
            fontSize = 18.sp
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = "Genre:",
            fontSize = 22.sp
        )

        LazyRow(
            content = {
                game.genre.forEach { genre ->
                    item {
                        FilledTonalButton(
                            onClick = {
                                // Handle button click here if needed
                            },
                            content = {
                                Text(text = genre)
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        )

        Text(
            modifier = Modifier.padding(8.dp),
            text = "Platforms:",
            fontSize = 22.sp
        )

        LazyRow(
            content = {
                game.platform.forEach { platform ->
                    item {
                        FilledTonalButton(
                            onClick = {
                                // Handle button click here if needed
                            },
                            content = {
                                Text(text = platform)
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = "Release date:",
            fontSize = 22.sp
        )

        FilledTonalButton(
            onClick = {
                // Handle button click here if needed
            },
            content = {
                Text(text = "${game.year}")
            },
            modifier = Modifier.padding(start = 8.dp)
        )

    }
}


@Preview
@Composable
fun DetailPageOverviewPreview() {
    DetailpageOverview(gamesList[0])
}
