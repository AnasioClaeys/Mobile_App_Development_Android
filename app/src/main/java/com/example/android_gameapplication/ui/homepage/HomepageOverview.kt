package com.example.android_gameapplication.ui.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.R
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.GameApiState
import com.example.android_gameapplication.network.PopularGamesOfAllTimeApiState
import com.example.android_gameapplication.network.PopularGamesOfThisYearApiState
import com.example.android_gameapplication.ui.ViewModel.GameViewModel

@Composable
fun HomepageOverview(
    onCarousel: (Int) -> Unit,
    onListPopularGamesAllTime: () -> Unit,
    onListPopularGamesOfThisYear: () -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
    val gameUiState by viewModel.gameUiState.collectAsState()
    val gamesList = gameUiState.gamesList
    val gameApiState = viewModel.gameApiState
    val popularGamesOfThisYearApiState = viewModel.popularGamesOfThisYearApiState
    val popularGamesOfAllTimeApiState = viewModel.popularGamesOfAllTimeApiState


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            //modifier padding top and bottom must be 16.dp
            .padding(top = 26.dp, bottom = 26.dp)
    ) {


        when (popularGamesOfThisYearApiState) {
            is PopularGamesOfThisYearApiState.Loading -> {
                Box(modifier = modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            alignment = Alignment.Center
                        )
                    )
                }
            }

            is PopularGamesOfThisYearApiState.Error -> Text(stringResource(R.string.couldn_t_load))
            is PopularGamesOfThisYearApiState.Success -> MainComponentHomepage(
                title = stringResource(R.string.home_title_popular_games_in_YEAR),
                gamesList = popularGamesOfThisYearApiState.games,
                onList = onListPopularGamesOfThisYear,
                onCarousel = onCarousel
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        when (popularGamesOfAllTimeApiState) {
            is PopularGamesOfAllTimeApiState.Loading -> {
                Box(modifier = modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            alignment = Alignment.Center
                        )
                    )
                }
            }

            is PopularGamesOfAllTimeApiState.Error -> Text(stringResource(R.string.couldn_t_load))
            is PopularGamesOfAllTimeApiState.Success -> MainComponentHomepage(
                title = stringResource(R.string.home_title_popular_games_of_all_time),
                gamesList = popularGamesOfAllTimeApiState.games,
                onList = onListPopularGamesAllTime,
                onCarousel = onCarousel
            )
        }

    }
}


/*@Preview
@Composable
fun HomepageOverviewPreview() {
    HomepageOverview()
}*/
