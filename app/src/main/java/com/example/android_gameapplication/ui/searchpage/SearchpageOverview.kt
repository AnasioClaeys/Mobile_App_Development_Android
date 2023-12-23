package com.example.android_gameapplication.ui.searchpage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android_gameapplication.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchpageOverview(onListItem: (Int) -> Unit, modifier: Modifier = Modifier) {
    val viewModel: SearchpageOverviewViewModel =
        viewModel(factory = SearchpageOverviewViewModel.Factory)
    val gameUiState by viewModel.searchpageOverviewState.collectAsState()

    val searchList = gameUiState.searchList
    val searchActive = gameUiState.searchActive
    val searchListHistory = gameUiState.searchListHistory

    val searchQuery = viewModel.searchQuery.collectAsState()

    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        SearchBar(
            query = searchQuery.value,
            onQueryChange = viewModel::onSearchTextChange,
            onSearch = {
                viewModel.onSearchActiveChange(false)
                if (searchQuery.value.isNotEmpty()) {
                    viewModel.addSearchListHistory(searchQuery.value)
                }
                if (searchQuery.value == "") {
                    viewModel.onSearchTextChange("")
                } else {
                    viewModel.onSearchTextChange(searchQuery.value)
                }
            },
            active = searchActive,
            onActiveChange = viewModel::onSearchActiveChange,
            modifier = Modifier.fillMaxWidth().testTag("test"),
            leadingIcon = {
                Icon(
                    Icons.Outlined.Search,
                    contentDescription = stringResource(R.string.search_icon),
                )
            },
            trailingIcon = {
                if (searchActive) {
                    Icon(
                        Icons.Outlined.Close,
                        modifier = Modifier.clickable {
                            if (searchQuery.value.isNotEmpty()) {
                                viewModel.onSearchTextChange("")
                            } else {
                                viewModel.onSearchActiveChange(false)
                            }
                        },
                        contentDescription = stringResource(R.string.close_search),
                    )
                }
            },
            placeholder = {
                Text(
                    text = stringResource(R.string.search_placeholder),
                )
            },
        ) {
            if (searchListHistory.isNotEmpty()) {
                searchListHistory.forEach {
                    Row(
                        modifier = Modifier
                            .padding(all = 14.dp)
                            .clickable { viewModel.onSearchTextChange(it) },
                    ) {
                        Icon(
                            Icons.Outlined.Menu,
                            contentDescription = stringResource(R.string.search_button),
                            modifier = Modifier.padding(end = 10.dp),
                        )
                        Text(
                            text = it,
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Divider()
                }
            }
            Text(
                text = stringResource(R.string.quick_results),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(all = 14.dp),
            )

            GamesList(
                gamesList = searchList,
                onListItem = onListItem,
                hasSearched = gameUiState.hasSearched,
                viewModel = viewModel,
            )
        }

        GamesList(
            gamesList = searchList,
            onListItem = onListItem,
            hasSearched = gameUiState.hasSearched,
            viewModel = viewModel,
        )
    }
}
