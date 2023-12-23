package com.example.android_gameapplication.model

/**
 * Data class representing a game.
 * This class holds all the necessary information about a game.
 *
 * @property id The unique identifier of the game.
 * @property name The name of the game.
 * @property genres A list of genres associated with the game.
 * @property released The release date of the game.
 * @property platforms A list of platforms on which the game is available.
 * @property backgroundImage URL for the background image of the game.
 * @property playtime The average playtime of the game in hours.
 * @property isPopularGamesOfAllTime Boolean flag indicating whether the game is considered among the most popular of all time.
 * @property isPopularGamesOfThisYear Boolean flag indicating whether the game is considered among the most popular of this year.
 */
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
