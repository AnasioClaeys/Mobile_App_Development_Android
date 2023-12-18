package com.example.android_gameapplication.ui.listpagePopularGamesThisYear

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.R
import com.example.android_gameapplication.network.PopularGamesOfThisYearApiState
import com.example.android_gameapplication.ui.ViewModel.GameViewModel
import com.example.android_gameapplication.ui.listpagePopularGamesAllTime.GameListPopularGames
import com.example.android_gameapplication.ui.searchpage.GamesList

@Composable
fun ListPageOverviewPopularGamesThisYear(onListItem: (Int) -> Unit, modifier: Modifier = Modifier) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)

    val uiListPopularGamesOfThisYearState by viewModel.uiListPopularGamesOfThisYearState.collectAsState()

    when (viewModel.popularGamesOfThisYearApiState) {
        is PopularGamesOfThisYearApiState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        alignment = Alignment.Center
                    )
                )
            }
        }

        is PopularGamesOfThisYearApiState.Error -> Text(text = stringResource(R.string.couldn_t_load))
        is PopularGamesOfThisYearApiState.Success -> GameListPopularGames(
            uiListPopularGamesOfThisYearState,
            onListItem,
            viewModel
        )
    }

}
