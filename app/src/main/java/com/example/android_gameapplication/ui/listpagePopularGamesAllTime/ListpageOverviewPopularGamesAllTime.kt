package com.example.android_gameapplication.ui.listpagePopularGamesAllTime

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.R
import com.example.android_gameapplication.network.PopularGamesOfAllTimeApiState
import com.example.android_gameapplication.network.PopularGamesOfThisYearApiState
import com.example.android_gameapplication.ui.ViewModel.GameViewModel
import com.example.android_gameapplication.ui.searchpage.GamesList

@Composable
fun ListpageOverviewPopularGamesAllTime(onListItem: (Int) -> Unit, modifier: Modifier = Modifier) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)

    when (val popularGamesOfAllTime = viewModel.popularGamesOfAllTimeApiState) {
        is PopularGamesOfAllTimeApiState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        alignment = Alignment.Center
                    )
                )
            }
        }

        is PopularGamesOfAllTimeApiState.Error -> Text(text = stringResource(R.string.couldn_t_load))
        is PopularGamesOfAllTimeApiState.Success -> GamesList(
            popularGamesOfAllTime.games,
            onListItem
        )
    }


}
