package com.example.android_gameapplication.ui.listpagePopularGamesThisYear

import com.example.android_gameapplication.model.Game

/**
 * Data class representing the state of the list page overview for the most played games of this year.
 * It holds the data and pagination information for the list of games.
 *
 * @property mostPlayedGamesOfThisYear The list of most played games of this year.
 * @property currentPageMostPlayedGamesOfThisYear The current page number in the pagination of most played games of this year.
 * @property lastPageMostPlayedGamesOfThisYear Boolean flag indicating whether the current page is the last page in the pagination for the most played games of this year.
 */
data class ListPageOverviewThisYearState(
    val mostPlayedGamesOfThisYear: List<Game> = listOf(),
    var currentPageMostPlayedGamesOfThisYear: Int = 1,
    var lastPageMostPlayedGamesOfThisYear: Boolean = false,
)
