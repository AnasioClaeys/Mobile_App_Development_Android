package com.example.android_gameapplication.network

import com.example.android_gameapplication.model.Game

sealed interface GameApiState {
    object Error: GameApiState
    object Loading: GameApiState

    data class Success(val games: List<Game>): GameApiState

}
