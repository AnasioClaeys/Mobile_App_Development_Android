package com.example.android_gameapplication.ui.searchpage

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.R
import com.example.android_gameapplication.ui.ViewModel.GameViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchpageOverview(onListItem: (Int) -> Unit, modifier: Modifier = Modifier) {
    val viewModel: GameViewModel = viewModel(factory = GameViewModel.Factory)
    val gameUiState by viewModel.gameUiState.collectAsState()

    val searchText = gameUiState.searchText
    val searchList = gameUiState.searchList
    val searchActive = gameUiState.searchActive
    val searchListHistory = gameUiState.searchListHistory


    Column(
        modifier = modifier.fillMaxWidth()
    ) {

        SearchBar(
            query = searchText,
            onQueryChange = viewModel::onSearchTextChange,
            onSearch = {
                viewModel.onSearchActiveChange(false)
                if(searchText.isNotEmpty()) {
                    viewModel.addSearchListHistory(searchText)

                }
                if(searchText==""){
                    viewModel.onSearchTextChange("")
                }
                else{
                    viewModel.setSearchText("")
                }
            },
            active = searchActive,
            onActiveChange = viewModel::onSearchActiveChange,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.search_placeholder)
                )
            },
            trailingIcon = {
                if (searchActive) {
                    Icon(
                        Icons.Outlined.Close,
                        modifier = Modifier.clickable {
                            if(searchText.isNotEmpty()) {
                                viewModel.onSearchTextChange("")
                            }
                            else{
                                viewModel.onSearchActiveChange(false)
                            }
                        },
                        contentDescription = stringResource(R.string.close_search)
                    )
                }
            },
            placeholder = { Text(text = stringResource(R.string.search_placeholder)) }
        ) {
            if(searchListHistory.isNotEmpty()){
                searchListHistory.forEach{
                    Row(
                        modifier = Modifier
                            .padding(all = 14.dp)
                            .clickable { viewModel.onSearchTextChange(it) }
                    ) {
                        Icon(
                            Icons.Outlined.Menu,
                            contentDescription = stringResource(R.string.search_placeholder),
                            modifier=Modifier.padding(end=10.dp),
                        )
                        Text(
                            text = it
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Divider()
                }
            }
            Text(text = stringResource(R.string.quick_results), style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(all=14.dp))

            GamesList(
                gamesList = searchList,
                onListItem = onListItem
            )

        }

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
