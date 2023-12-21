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

@Composable
fun DetailpageOverview(
    gameId: Int,
    modifier: Modifier = Modifier,
    detailpageOverviewViewModel: DetailpageOverviewViewModel = viewModel(factory = DetailpageOverviewViewModel.Factory)
) {
    LaunchedEffect(key1 = gameId) {
        detailpageOverviewViewModel.getDetailGameById(gameId)
    }

    when (val gameDetailApiState =
        detailpageOverviewViewModel.gameDetailApiState.collectAsState().value) {
        is DetailGameApiState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        alignment = Alignment.Center
                    )
                )
            }
        }

        is DetailGameApiState.Error -> {
            Text(text = stringResource(R.string.error))
        }

        is DetailGameApiState.Success -> {
            val game = gameDetailApiState.game

            Detailpage(game = game, modifier = modifier)
        }
    }

}
