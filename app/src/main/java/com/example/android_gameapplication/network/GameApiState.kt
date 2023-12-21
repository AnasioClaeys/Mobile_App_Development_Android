package com.example.android_gameapplication.network

import com.example.android_gameapplication.model.Game

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

sealed interface SearchGameApiState {
    object Loading : SearchGameApiState
    object Error : SearchGameApiState
    data class Success(val games: List<Game>) : SearchGameApiState
}

sealed interface MostPlayedGamesOfThisYearApiState {
    object Error : MostPlayedGamesOfThisYearApiState
    object Loading : MostPlayedGamesOfThisYearApiState
    object Success : MostPlayedGamesOfThisYearApiState
}

sealed interface MostPlayedGamesOfAllTimeApiState {
    object Error : MostPlayedGamesOfAllTimeApiState
    object Loading : MostPlayedGamesOfAllTimeApiState
    object Success : MostPlayedGamesOfAllTimeApiState
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
