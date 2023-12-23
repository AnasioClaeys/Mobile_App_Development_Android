package com.example.android_gameapplication.ui.listpagePopularGamesAllTime

import com.example.android_gameapplication.model.Game

/**
 * Data class representing the state of the list page overview for the most played games of all time.
 * It holds the data and pagination information for the list of games.
 *
 * @property mostPlayedGamesOfAllTime The list of most played games of all time.
 * @property currentPageMostPlayedGamesOfAllTime The current page number in the pagination of most played games of all time.
 * @property lastPageMostPlayedGamesOfTAllTime Boolean flag indicating whether the current page is the last page in the pagination for the most played games of all time.
 */
data class ListpageOverviewAllTimeState(
    val mostPlayedGamesOfAllTime: List<Game> = listOf(),
    var currentPageMostPlayedGamesOfAllTime: Int = 1,
    var lastPageMostPlayedGamesOfTAllTime: Boolean = false,
)
