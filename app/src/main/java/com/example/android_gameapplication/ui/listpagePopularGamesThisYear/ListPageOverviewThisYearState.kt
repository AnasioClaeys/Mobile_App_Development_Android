package com.example.android_gameapplication.ui.listpagePopularGamesThisYear

import com.example.android_gameapplication.model.Game

data class ListPageOverviewThisYearState(
    val mostPlayedGamesOfThisYear: List<Game> = listOf(),
)