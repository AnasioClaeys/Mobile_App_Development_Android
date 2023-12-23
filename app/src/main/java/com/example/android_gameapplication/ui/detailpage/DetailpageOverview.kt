package com.example.android_gameapplication.ui.detailpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.R
import com.example.android_gameapplication.network.DetailGameApiState

/**
 * A Composable function that provides an overview of the detail page based on the game ID.
 * It handles the state of the game detail data, displaying a loading indicator, error message, or the game detail view.
 *
 * @param gameId The unique identifier of the game.
 * @param modifier The modifier to be applied to the layout.
 * @param detailpageOverviewViewModel The ViewModel that manages the state of the game detail data.
 */
@Composable
fun DetailpageOverview(
    gameId: Int,
    modifier: Modifier = Modifier,
    detailpageOverviewViewModel: DetailpageOverviewViewModel = viewModel(factory = DetailpageOverviewViewModel.Factory),
) {
    // Trigger data loading effect based on gameId
    LaunchedEffect(key1 = gameId) {
        detailpageOverviewViewModel.getDetailGameById(gameId)
    }

    // Rendering UI based on the game detail state
    when (
        val gameDetailApiState =
            detailpageOverviewViewModel.gameDetailApiState.collectAsState().value
    ) {
        // Displaying a loading indicator during data fetching
        is DetailGameApiState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        alignment = Alignment.Center,
                    ),
                )
            }
        }

        // Displaying an error message if data fetching fails
        is DetailGameApiState.Error -> {
            Text(text = stringResource(R.string.error))
        }

        // Displaying the game details if data fetching is successful
        is DetailGameApiState.Success -> {
            val game = gameDetailApiState.game

            Detailpage(game = game, modifier = modifier)
        }
    }
}
