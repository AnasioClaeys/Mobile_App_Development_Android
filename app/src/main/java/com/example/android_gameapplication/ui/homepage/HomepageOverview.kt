package com.example.android_gameapplication.ui.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.android_gameapplication.data.gamesList
import com.example.android_gameapplication.ui.ViewModel.GameViewModel

@Composable
fun HomepageOverview(onCarousel: (Int) -> Unit,modifier: Modifier = Modifier) {
    val viewModel: GameViewModel = viewModel()
    val gameUiState by viewModel.gameUiState.collectAsState()
    val gamesList = gameUiState.gamesList

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainComponentHomepage(
            title = "Popular games",
            gamesList = gamesList,
            onCarousel = onCarousel
        )

        Spacer(modifier = modifier.height(8.dp))

        MainComponentHomepage(
            title = "Recently released",
            gamesList = gamesList,
            onCarousel = onCarousel
        )

    }
}


/*@Preview
@Composable
fun HomepageOverviewPreview() {
    HomepageOverview()
}*/
