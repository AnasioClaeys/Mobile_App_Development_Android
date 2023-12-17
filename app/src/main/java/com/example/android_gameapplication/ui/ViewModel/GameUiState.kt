package com.example.android_gameapplication.ui.ViewModel

import com.example.android_gameapplication.model.Game

data class GameUiState(
    val gamesList: List<Game> = listOf(),
    val popularGamesOfThisYear: List<Game> = listOf(),
    val popularGamesOfAllTime: List<Game> = listOf(),

    val searchText: String = "",
    val searchList: List<Game> = listOf(),
    val searchActive: Boolean = false,
    val searchListHistory: List<String> = listOf(),
    val hasSearched: Boolean = false
)
