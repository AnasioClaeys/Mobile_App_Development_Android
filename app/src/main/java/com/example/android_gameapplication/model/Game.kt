package com.example.android_gameapplication.model


data class Game(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val released: String,
    val platforms: List<String>,
    val backgroundImage: String,
    val playtime: Int,
    val isPopularGamesOfAllTime: Boolean = false,
    val isPopularGamesOfThisYear: Boolean = false,
)
