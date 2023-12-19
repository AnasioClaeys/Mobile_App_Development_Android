package com.example.android_gameapplication.ui.searchpage

import com.example.android_gameapplication.model.Game

data class SearchpageOverviewState(
    val searchText: String = "",
    val searchList: List<Game> = listOf(),
    val searchActive: Boolean = false,
    val searchListHistory: List<String> = listOf(),
    val hasSearched: Boolean = false,
)
