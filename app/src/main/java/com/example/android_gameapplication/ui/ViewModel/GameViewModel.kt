package com.example.android_gameapplication.ui.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.GameApi.gameService
import com.example.android_gameapplication.network.GameApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.android_gameapplication.network.asDomainObjects


class GameViewModel : ViewModel() {
    private val _gameUiState = MutableStateFlow(GameUiState(gamesList= Game.getAllGames(), searchList = Game.getAllGames()))
    val gameUiState = _gameUiState.asStateFlow()

    //**********************************************************************************************************************
    //api

    var gameApiState: GameApiState by mutableStateOf(GameApiState.Loading)
        private set

    init {
        getApiGames()
        getMostPopularGamesOfThisYear()
        getMostPopularGamesOfAllTime()
    }

    private fun getApiGames() {
        viewModelScope.launch {

                val result = gameService.getGames()
                gameApiState = GameApiState.Success(result.asDomainObjects())
                Log.i("getApiGames", "getApiGames: ${result.asDomainObjects()}}")
        }
    }

    private fun getMostPopularGamesOfThisYear(){
        viewModelScope.launch {
            val result = gameService.getMostPopularGamesOfThisYear()
            gameApiState = GameApiState.Success(result.asDomainObjects())

            Log.i("getMostPopularGamesOfThisYear", "getMostPopularGamesOfThisYear: ${result.asDomainObjects()}}")
        }
    }

    private fun getMostPopularGamesOfAllTime(){
        viewModelScope.launch {
            val result = gameService.getMostPopularGamesOfAllTime()
            gameApiState = GameApiState.Success(result.asDomainObjects())

            Log.i("getMostPopularGamesOfAllTime", "getMostPopularGamesOfAllTime: ${result.asDomainObjects()}}")
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
                    currentState.gamesList.filter { it.name.contains(text, ignoreCase = true) }
                }
            )
        }
    }

}
