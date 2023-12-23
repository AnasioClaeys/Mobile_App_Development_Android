package com.example.android_gameapplication.ui.homepage

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.android_gameapplication.R
import com.example.android_gameapplication.model.Game

/**
 * A Composable function that creates a main component for the homepage.
 * It includes a title, a carousel of games, and a button to view more games.
 *
 * @param title The title for the main component.
 * @param gamesList The list of [Game] objects to be displayed in the carousel.
 * @param onCarousel A lambda function to be invoked when a game in the carousel is clicked, passing the game's ID.
 * @param onList A lambda function to be invoked when the button is clicked.
 * @param buttonText The resource ID for the text to be displayed on the button.
 */
@Composable
fun MainComponentHomepage(
    title: String,
    gamesList: List<Game>,
    onCarousel: (Int) -> Unit,
    onList: () -> Unit,
    @StringRes buttonText: Int,
) {
    // Title text
    Text(
        text = title,
        style = MaterialTheme.typography.headlineMedium,
    )

    // Carousel of games
    Carousel(
        gamesList = gamesList,
        onCarousel = onCarousel,
    )

    // Button for additional actions
    FilledTonalButton(
        onClick = { onList() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = buttonText),
                style = MaterialTheme.typography.labelLarge,
            )
            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = stringResource(R.string.arrow_icon),
            )
        }
    }
}
