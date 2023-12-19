package com.example.android_gameapplication.ui.listpagePopularGamesAllTime

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
import com.example.android_gameapplication.network.MostPlayedGamesOfAllTimeApiState

@Composable
fun ListpageOverviewPopularGamesAllTime(onListItem: (Int) -> Unit, modifier: Modifier = Modifier) {
    val viewModel: ListpageOverviewAllTimeViewModel = viewModel(factory = ListpageOverviewAllTimeViewModel.Factory)

    val mostPlayedGamesOfAllTimeApiState = viewModel.mostPlayedGamesOfAllTimeApiState
    val listpageOverviewAllTimeState by viewModel.listpageOverviewAllTimeState.collectAsState()

    when (mostPlayedGamesOfAllTimeApiState) {
        is MostPlayedGamesOfAllTimeApiState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        alignment = Alignment.Center
                    )
                )
            }
        }

        is MostPlayedGamesOfAllTimeApiState.Error -> Text(text = stringResource(R.string.couldn_t_load))
        is MostPlayedGamesOfAllTimeApiState.Success -> GameListPopularGames(
            listpageOverviewAllTimeState.mostPlayedGamesOfAllTime,
            onListItem,
            newPage = viewModel::loadNextPageMostPlayedGamesOfAllTime
        )
    }


}
