package com.example.android_gameapplication.ui.homepage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_gameapplication.model.Game

/**
 * A Composable function that creates a card for a game.
 * Each card displays the game's image and name and can be clicked to trigger a specified action.
 *
 * @param game The [Game] object containing information to be displayed in the card.
 * @param onCarousel A lambda function to be invoked when the card is clicked, passing the game's ID.
 * @param modifier The modifier to be applied to the card layout.
 */
@Composable
fun GameCard(game: Game, onCarousel: (Int) -> Unit, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier
            .padding(8.dp)
            .height(210.dp)
            .clickable(onClick = { onCarousel(game.id) }),

        ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .width(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                model = game.backgroundImage,
                contentDescription = game.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(shape = RoundedCornerShape(6.dp)),
            )

            Column(
                modifier = modifier
                    .height(70.dp)
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = game.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

/**
 * A Composable function that creates a carousel of game cards.
 * It lays out game cards horizontally and allows users to scroll through them.
 *
 * @param gamesList The list of [Game] objects to be displayed in the carousel.
 * @param onCarousel A lambda function to be invoked when a game card is clicked, passing the game's ID.
 */
@Composable
fun Carousel(gamesList: List<Game>, onCarousel: (Int) -> Unit) {
    LazyRow(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
    ) {
        items(gamesList) { game ->
            GameCard(
                game = game,
                onCarousel = onCarousel,
            )
        }
    }
}
