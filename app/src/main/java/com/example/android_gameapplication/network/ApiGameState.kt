package com.example.android_gameapplication.network

sealed interface ApiGameState {
    object Error: ApiGameState
    object Loading: ApiGameState

    data class Success(val games: List<ApiGame>): ApiGameState

}
