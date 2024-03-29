package com.example.android_gameapplication.ui.listpagePopularGamesAllTime

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.android_gameapplication.R
import com.example.android_gameapplication.model.Game

/**
 * A Composable function that creates a list item for popular games.
 * Each item displays game details and is clickable to perform a specified action.
 *
 * @param game The [Game] object containing information to be displayed in the list item.
 * @param onListItem A lambda function to be invoked when the list item is clicked, passing the game's ID.
 * @param modifier The modifier to be applied to the list item layout.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListItemPopularGames(game: Game, onListItem: (Int) -> Unit, modifier: Modifier = Modifier) {
    ListItem(
        modifier = modifier
            .padding(6.dp)
            .clickable(onClick = { onListItem(game.id) }),
        headlineContent = { Text(text = game.name, style = MaterialTheme.typography.titleMedium) },
        leadingContent = {
            AsyncImage(
                model = game.backgroundImage,
                contentDescription = game.name,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(70.dp)
                    .clip(
                        RoundedCornerShape(16.dp),
                    ),
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Outlined.ArrowForward,
                contentDescription = stringResource(
                    R.string.listItem_icon_go_to_detail_page,
                ),
            )
        },
    )
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp),
    )
}

/**
 * A Composable function that creates a list of popular games.
 * It displays a list of game items and handles loading new pages of games.
 *
 * @param gamesList The list of [Game] objects to be displayed.
 * @param onListItem A lambda function to be invoked when a list item is clicked, passing the game's ID.
 * @param newPage A lambda function to be invoked to load a new page of games.
 */
@Composable
fun GameListPopularGames(
    gamesList: List<Game>,
    onListItem: (Int) -> Unit,
    newPage: () -> Unit = {},
) {
    if (gamesList.isNullOrEmpty()) {
        Text(
            text = stringResource(R.string.no_games_found2),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp),
        )
    } else {
        LazyColumn {
            items(gamesList) { game ->
                GameListItemPopularGames(game = game, onListItem = onListItem)
            }

            if (gamesList.isNotEmpty()) {
                item {
                    Box(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(
                                alignment = Alignment.Center,
                            ),
                        )
                    }
                    LaunchedEffect(gamesList.size) {
                        newPage()
                    }
                }
            }
        }
    }
}
