package com.example.android_gameapplication.ui.searchpage

import com.example.android_gameapplication.model.Game

/**
 * Represents the state of the search page in the Android game application.
 *
 * @property searchList The list of games displayed in the search results.
 * @property searchActive Indicates whether a search is currently active.
 * @property searchListHistory The history of search queries.
 * @property hasSearched Indicates whether a search has been performed.
 * @property currentPage The current page of search results.
 * @property isLoading Indicates whether data is being loaded.
 * @property lastSearchQuery The most recent search query.
 * @property isLastPage Indicates whether the current page is the last page of search results.
 */
data class SearchpageOverviewState(
    val searchList: List<Game> = listOf(),
    val searchActive: Boolean = false,
    val searchListHistory: List<String> = listOf(),
    val hasSearched: Boolean = false,
    var currentPage: Int = 1,
    var isLoading: Boolean = false,
    var lastSearchQuery: String = "",
    var isLastPage: Boolean = false,
)
