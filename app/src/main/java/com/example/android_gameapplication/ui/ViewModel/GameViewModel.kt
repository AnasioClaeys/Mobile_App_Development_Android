package com.example.android_gameapplication.ui.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_gameapplication.data.gamesList
import com.example.android_gameapplication.ui.model.Game
import com.example.android_gameapplication.ui.network.GameApi.gameService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {
    private val _gameUiState = MutableStateFlow(GameUiState(gamesList= Game.getAllGames(), searchList = Game.getAllGames()))
    val gameUiState = _gameUiState.asStateFlow()

    //**********************************************************************************************************************
    //api
    init {
        getApiGames()
    }

    private fun getApiGames() {
        viewModelScope.launch {
            try {
                val response = gameService.getGames()
                val games = response.results

                println("The tasks: $games")
            } catch (e: Exception) {
                Log.e("GameViewModel", "Error fetching games from API", e)
                // Handle error, e.g., show a message to the user
            }
        }
    }



    //**********************************************************************************************************************

    //gameUiState to getGameById
    fun getGameById(gameId: Int): Game {
        return gameUiState.value.gamesList.find { it.id == gameId }!!
    }


    //**********************************************************************************************************************
    //SEARCH
//    private val _searchText = MutableStateFlow("")
//    val searchText = _searchText.asStateFlow()

//    fun onSearchTextChange(text: String) {
//        _gameUiState.update {
//            it.copy(searchText = text)
//        }
////        _searchText.value = text
//    }

//    private val _searchList = MutableStateFlow(gameUiState.value.gamesList)
//    val searchList = gameUiState
//        .combine(_searchList) { uiState, searchList ->
//            if (uiState.searchText.isEmpty()) {
//                searchList
//            } else {
//                searchList.filter { it.title.contains(uiState.searchText, ignoreCase = true) }
//            }
//        }
//        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _searchList.value)

    //**********************************************************************************************************************
    //SEARCH

    fun onSearchTextChange(text: String) {
        _gameUiState.update { currentState ->
            currentState.copy(
                searchText = text,
                searchList = if (text.isEmpty()) {
                    currentState.gamesList
                } else {
                    currentState.gamesList.filter { it.title.contains(text, ignoreCase = true) }
                }
            )
        }
    }

}
