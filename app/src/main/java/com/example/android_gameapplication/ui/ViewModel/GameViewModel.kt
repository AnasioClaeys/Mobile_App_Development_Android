package com.example.android_gameapplication.ui.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.DetailGameApiState
import com.example.android_gameapplication.network.GameApi.gameService
import com.example.android_gameapplication.network.GameApiState
import com.example.android_gameapplication.network.PopularGamesOfAllTimeApiState
import com.example.android_gameapplication.network.PopularGamesOfThisYearApiState
import com.example.android_gameapplication.network.asDomainObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.android_gameapplication.network.asDomainObjects


class GameViewModel : ViewModel() {
    private val _gameUiState = MutableStateFlow(
        GameUiState(
            gamesList = Game.getAllGames(),
            searchList = Game.getAllGames()
        )
    )
    val gameUiState = _gameUiState.asStateFlow()

    //**********************************************************************************************************************
    //api

    var gameApiState: GameApiState by mutableStateOf(GameApiState.Loading)
        private set

    var gameDetailApiState: DetailGameApiState by mutableStateOf(DetailGameApiState.Loading)
        private set

    var popularGamesOfThisYearApiState: PopularGamesOfThisYearApiState by mutableStateOf(
        PopularGamesOfThisYearApiState.Loading
    )
        private set

    var popularGamesOfAllTimeApiState: PopularGamesOfAllTimeApiState by mutableStateOf(
        PopularGamesOfAllTimeApiState.Loading
    )
        private set


    init {
        getApiGames()
        getMostPopularGamesOfThisYear()
        getMostPopularGamesOfAllTime()
        getDetailGameById(3498)
    }

    private fun getApiGames() {

        viewModelScope.launch {
            try {
                //Ophalen van de data
                val result = gameService.getGames()

                //Update de beginstate van gameslist en searchlist
                _gameUiState.update { currentState ->
                    currentState.copy(
                        gamesList = result.asDomainObjects(),
                        searchList = result.asDomainObjects()
                    )
                }

                //Update de gameApiState
                gameApiState = GameApiState.Success(result.asDomainObjects())

                //Log de resultaten
                Log.i("getApiGames", "getApiGames: ${result.asDomainObjects()}}")
            }
            catch (e: Exception) {
                gameApiState = GameApiState.Error
            }

        }
    }

    fun getDetailGameById(gameId: Int) {

        viewModelScope.launch {
            try {
                //Ophalen van de data
                val result = gameService.getGameDetailById(gameId)


                //Update de DetailGameApiState
                gameDetailApiState = DetailGameApiState.Success(result.asDomainObject())

                //Log de resultaten
                Log.i("getDetailGameById", "getDetailGameById: ${result}}")
            }
            catch (e: Exception) {
                gameDetailApiState = DetailGameApiState.Error
            }
        }
    }

    private fun getMostPopularGamesOfThisYear() {
        viewModelScope.launch {
            try {
                //Ophalen van de data
                val result = gameService.getMostPopularGamesOfThisYear()

                //Update de PopularGamesOfThisYearApiState
                popularGamesOfThisYearApiState =
                    PopularGamesOfThisYearApiState.Success(result.asDomainObjects())

                //Log de resultaten
                Log.i(
                    "getMostPopularGamesOfThisYear",
                    "getMostPopularGamesOfThisYear: ${result.asDomainObjects()}}"
                )
            }
            catch (e: Exception) {
                popularGamesOfThisYearApiState = PopularGamesOfThisYearApiState.Error
            }
        }
    }

    private fun getMostPopularGamesOfAllTime() {
        viewModelScope.launch {
            try {
                //Ophalen van de data
                val result = gameService.getMostPopularGamesOfAllTime()

                //Update de PopularGamesOfAllTimeApiState
                popularGamesOfAllTimeApiState =
                    PopularGamesOfAllTimeApiState.Success(result.asDomainObjects())

                //Log de resultaten
                Log.i(
                    "getMostPopularGamesOfAllTime",
                    "getMostPopularGamesOfAllTime: ${result.asDomainObjects()}}"
                )
            }
            catch (e: Exception) {
                popularGamesOfAllTimeApiState = PopularGamesOfAllTimeApiState.Error
            }
        }
    }

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
