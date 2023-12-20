package com.example.android_gameapplication.ui.listpagePopularGamesAllTime

import com.example.android_gameapplication.model.Game

data class ListpageOverviewAllTimeState(
    val mostPlayedGamesOfAllTime: List<Game> = listOf(),
    var currentPageMostPlayedGamesOfAllTime: Int = 1,
    var lastPageMostPlayedGamesOfTAllTime: Boolean = false
)
