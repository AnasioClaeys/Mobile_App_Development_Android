package com.example.android_gameapplication.network

import com.example.android_gameapplication.model.Game
import kotlinx.coroutines.flow.Flow

sealed interface GameApiState {
    //object Error: GameApiState met e van Exception
    object Error : GameApiState
    object Loading : GameApiState

    data class Success(val games: List<Game>) : GameApiState
}

sealed interface DetailGameApiState {
    object Error : DetailGameApiState
    object Loading : DetailGameApiState

    data class Success(val game: Game) : DetailGameApiState
}

sealed interface PopularGamesOfThisYearApiState {
    object Error : PopularGamesOfThisYearApiState
    object Loading : PopularGamesOfThisYearApiState
    object Success : PopularGamesOfThisYearApiState
}

sealed interface PopularGamesOfAllTimeApiState {
    object Error : PopularGamesOfAllTimeApiState
    object Loading : PopularGamesOfAllTimeApiState
    object Success : PopularGamesOfAllTimeApiState
}
