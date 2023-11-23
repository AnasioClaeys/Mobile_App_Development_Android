package com.example.android_gameapplication.ui.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.network.GameApiState
import com.example.android_gameapplication.network.PopularGamesOfAllTimeApiState
import com.example.android_gameapplication.network.PopularGamesOfThisYearApiState
import com.example.android_gameapplication.ui.ViewModel.GameViewModel

@Composable
fun HomepageOverview(onCarousel: (Int) -> Unit,modifier: Modifier = Modifier) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
    val gameUiState by viewModel.gameUiState.collectAsState()
    val gamesList = gameUiState.gamesList
    val gameApiState = viewModel.gameApiState
    val popularGamesOfThisYearApiState = viewModel.popularGamesOfThisYearApiState
    val popularGamesOfAllTimeApiState = viewModel.popularGamesOfAllTimeApiState


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        when(popularGamesOfThisYearApiState){
            is PopularGamesOfThisYearApiState.Loading -> Text("Loading...")
            is PopularGamesOfThisYearApiState.Error -> Text("Couldn't load...")
            is PopularGamesOfThisYearApiState.Success -> MainComponentHomepage(
                title = "Popular games in 2023",
                gamesList = popularGamesOfThisYearApiState.games,
                onCarousel = onCarousel
            )
        }



        Spacer(modifier = modifier.height(8.dp))

        when(popularGamesOfAllTimeApiState){
            is PopularGamesOfAllTimeApiState.Loading -> Text("Loading...")
            is PopularGamesOfAllTimeApiState.Error -> Text("Couldn't load...")
            is PopularGamesOfAllTimeApiState.Success -> MainComponentHomepage(
                title = "Popular games of all time",
                gamesList = popularGamesOfAllTimeApiState.games,
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
