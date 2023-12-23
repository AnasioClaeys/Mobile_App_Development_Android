package com.example.android_gameapplication.ui.detailpage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.android_gameapplication.R
import com.example.android_gameapplication.model.Game

/**
 * A Composable function that creates the detail page for a game.
 * It displays various information about the game including the background image, name, genres, platforms, release date, and playtime.
 *
 * @param game The [Game] object containing the details to be displayed.
 * @param modifier The modifier to be applied to the layout.
 */
@Composable
fun Detailpage(game: Game, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
    ) {
        item {
            AsyncImage(
                model = game.backgroundImage,
                contentDescription = game.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
            )

            Text(
                modifier = modifier.padding(8.dp).padding(start = 10.dp),
                text = "${game.name}",
                fontSize = 28.sp,
            )

            ComponentRow(stringResource(R.string.detail_title_genres), game.genres)

            ComponentRow(stringResource(R.string.detail_title_platforms), game.platforms)

            ComponentRow(
                title = stringResource(R.string.detail_title_release_date),
                component = listOf(game.released),
            )

            ComponentRow(
                title = stringResource(R.string.detail_title_average_playtime),
                component = listOf(
                    stringResource(
                        R.string.detail_item_playtime_hours,
                        game.playtime,
                    ),
                ),
            )
        }
    }
}
