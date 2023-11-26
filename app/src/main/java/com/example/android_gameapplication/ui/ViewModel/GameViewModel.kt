package com.example.android_gameapplication.ui.ViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.android_gameapplication.GamesApplication
import com.example.android_gameapplication.data.GamesRepository
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.DetailGameApiState
import com.example.android_gameapplication.network.GameApiState
import com.example.android_gameapplication.network.PopularGamesOfAllTimeApiState
import com.example.android_gameapplication.network.PopularGamesOfThisYearApiState
import com.example.android_gameapplication.network.asDomainObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.android_gameapplication.network.asDomainObjects


class GameViewModel(
    private val gamesRepository: GamesRepository) : ViewModel() {
    private val _gameUiState = MutableStateFlow(
        GameUiState(
            gamesList = emptyList(),
            searchList = emptyList(),
            searchText = ""
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
    }

    private fun getApiGames() {

        viewModelScope.launch {
            try {
                //Ophalen van de data
                val result = gamesRepository.getGames()

                //Update de beginstate van gameslist en searchlist
                _gameUiState.update { currentState ->
                    currentState.copy(
                        gamesList = result,
                        searchList = result
                    )
                }

                //Update de gameApiState
                gameApiState = GameApiState.Success(result)

                //Log de resultaten
                Log.i("getApiGames", "getApiGames: ${result}}")
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
                val result = gamesRepository.getDetailGameById(gameId)


                //Update de DetailGameApiState
                gameDetailApiState = DetailGameApiState.Success(result)

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
                val result = gamesRepository.getMostPopularGamesOfThisYear()

                //Update de PopularGamesOfThisYearApiState
                popularGamesOfThisYearApiState =
                    PopularGamesOfThisYearApiState.Success(result)

                //Log de resultaten
                Log.i(
                    "getMostPopularGamesOfThisYear",
                    "getMostPopularGamesOfThisYear: ${result}}"
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
                val result = gamesRepository.getMostPopularGamesOfAllTime()

                //Update de PopularGamesOfAllTimeApiState
                popularGamesOfAllTimeApiState =
                    PopularGamesOfAllTimeApiState.Success(result)

                //Log de resultaten
                Log.i(
                    "getMostPopularGamesOfAllTime",
                    "getMostPopularGamesOfAllTime: ${result}}"
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

    //**********************************************************************************************************************
    //REPOSITORY
    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as GamesApplication
                val gamesRepository = application.container.gamesRepository
                GameViewModel(gamesRepository)
            }
        }
    }

}
