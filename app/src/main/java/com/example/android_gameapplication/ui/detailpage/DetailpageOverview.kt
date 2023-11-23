package com.example.android_gameapplication.ui.detailpage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.network.DetailGameApiState
import com.example.android_gameapplication.ui.ViewModel.GameViewModel

@Composable
fun DetailpageOverview(
    gameId: Int,
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
) {
    LaunchedEffect(key1 = gameId){
        gameViewModel.getDetailGameById(gameId)
    }

    when (val gameDetailApiState = gameViewModel.gameDetailApiState) {
        is DetailGameApiState.Loading -> {
            Text(text = "Loading...")
        }

        is DetailGameApiState.Error -> {
            Text(text = "Error")
        }

        is DetailGameApiState.Success -> {
            val game = gameDetailApiState.game

            Detailpage(game = game, modifier = modifier)
        }
    }

}
