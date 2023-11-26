package com.example.android_gameapplication.ui.searchpage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.ui.ViewModel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchpageOverview(onListItem: (Int) -> Unit, modifier: Modifier = Modifier) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
    val gameUiState by viewModel.gameUiState.collectAsState()

    val searchText = gameUiState.searchText
    val searchList = gameUiState.searchList

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChange,
            modifier = modifier.fillMaxWidth(),
            placeholder = { Text(text = "Search") })

        GamesList(
            gamesList = searchList,
            onListItem = onListItem
        )

    }

}

/*
@Preview
@Composable
fun SearchpageOverviewPreview() {
    SearchpageOverview()
}
*/
