package com.example.android_gameapplication.network

import com.example.android_gameapplication.model.Game

/**
 * Sealed interface representing the state of a generic game-related API operation.
 */
sealed interface GameApiState {
    object Error : GameApiState
    object Loading : GameApiState
    data class Success(val games: List<Game>) : GameApiState
}

/**
 * Sealed interface representing the state of the API operation for fetching details of a specific game.
 */
sealed interface DetailGameApiState {
    object Error : DetailGameApiState
    object Loading : DetailGameApiState

    data class Success(val game: Game) : DetailGameApiState
}

/**
 * Sealed interface representing the state of the API operation for searching games.
 */
sealed interface SearchGameApiState {
    object Loading : SearchGameApiState
    object Error : SearchGameApiState
    data class Success(val games: List<Game>) : SearchGameApiState
}

/**
 * Sealed interface representing the state of the API operation for fetching the most played games of the current year.
 */
sealed interface MostPlayedGamesOfThisYearApiState {
    object Error : MostPlayedGamesOfThisYearApiState
    object Loading : MostPlayedGamesOfThisYearApiState
    object Success : MostPlayedGamesOfThisYearApiState
}

/**
 * Sealed interface representing the state of the API operation for fetching the most played games of all time.
 */
sealed interface MostPlayedGamesOfAllTimeApiState {
    object Error : MostPlayedGamesOfAllTimeApiState
    object Loading : MostPlayedGamesOfAllTimeApiState
    object Success : MostPlayedGamesOfAllTimeApiState
}

/**
 * Sealed interface representing the state of the API operation for fetching the most popular games of the current year.
 */
sealed interface PopularGamesOfThisYearApiState {
    object Error : PopularGamesOfThisYearApiState
    object Loading : PopularGamesOfThisYearApiState
    object Success : PopularGamesOfThisYearApiState
}

/**
 * Sealed interface representing the state of the API operation for fetching the most popular games of all time.
 */
sealed interface PopularGamesOfAllTimeApiState {
    object Error : PopularGamesOfAllTimeApiState
    object Loading : PopularGamesOfAllTimeApiState
    object Success : PopularGamesOfAllTimeApiState
}
