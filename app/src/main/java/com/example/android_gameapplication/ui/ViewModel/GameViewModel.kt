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
import com.example.android_gameapplication.data.GameRepository
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.DetailGameApiState
import com.example.android_gameapplication.network.GameApiState
import com.example.android_gameapplication.network.PopularGamesOfAllTimeApiState
import com.example.android_gameapplication.network.PopularGamesOfThisYearApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class GameViewModel(
    private val gameRepository: GameRepository
) : ViewModel() {
    private val _gameUiState = MutableStateFlow(
        GameUiState(
            gamesList = emptyList(),
            searchList = emptyList(),
            searchText = "",
            searchActive = false,
            searchListHistory = emptyList()
        )
    )
    val gameUiState = _gameUiState.asStateFlow()

    //**********************************************************************************************************************
    //Detailpage from list to string with , and space
//    fun listToString(list: List<String>): String {
//        var string = ""
//        for (i in list.indices) {
//            string += list[i]
//            if (i != list.size - 1) {
//                string += ", "
//            }
//        }
//        return string
//    }

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

    lateinit var uiListPopularGamesOfThisYearState: StateFlow<List<Game>>

    lateinit var uiListPopularGamesOfAllTimeState: StateFlow<List<Game>>


    init {
        getApiGames()
        getMostPopularGamesOfThisYear()
        getMostPopularGamesOfAllTime()
    }

    private fun getApiGames() {

        viewModelScope.launch {
            try {
                //Ophalen van de data
                val result = gameRepository.getGames()

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
//                Log.i("getApiGames", "getApiGames: ${result}}")
            } catch (e: Exception) {
                gameApiState = GameApiState.Error
            }

        }
    }

    fun getDetailGameById(gameId: Int) {

        viewModelScope.launch {
            try {
                //Ophalen van de data
                val result = gameRepository.getDetailGameById(gameId)


                //Update de DetailGameApiState
                gameDetailApiState = DetailGameApiState.Success(result)

                //Log de resultaten
//                Log.i("getDetailGameById", "getDetailGameById: ${result}}")
            } catch (e: Exception) {
                gameDetailApiState = DetailGameApiState.Error
            }
        }
    }

    private fun getMostPopularGamesOfThisYear() {
        try {
            viewModelScope.launch {
                gameRepository.refresh()
            }

                //Ophalen van de data
//                val result = gameRepository.getMostPopularGamesOfThisYear()
            uiListPopularGamesOfThisYearState = gameRepository.getMostPopularGamesOfThisYear().stateIn(
                scope = viewModelScope,
                started= SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )

                //Update de PopularGamesOfThisYearApiState
                popularGamesOfThisYearApiState =
                    PopularGamesOfThisYearApiState.Success

                //Log de resultaten
//                Log.i(
//                    "getMostPopularGamesOfThisYear",
//                    "getMostPopularGamesOfThisYear: ${result}}"
//                )


        } catch (e: Exception) {
            popularGamesOfThisYearApiState = PopularGamesOfThisYearApiState.Error
        }
    }

    private fun getMostPopularGamesOfAllTime() {
        try {
        viewModelScope.launch {
            gameRepository.refresh()
        }

                //Ophalen van de data
//                val result = gameRepository.getMostPopularGamesOfAllTime()
            uiListPopularGamesOfAllTimeState = gameRepository.getMostPopularGamesOfAllTime().stateIn(
                scope = viewModelScope,
                started= SharingStarted.WhileSubscribed(5_000L),
                initialValue = listOf()
            )


                //Update de PopularGamesOfAllTimeApiState
                popularGamesOfAllTimeApiState =
                    PopularGamesOfAllTimeApiState.Success

                //Log de resultaten
//                Log.i(
//                    "getMostPopularGamesOfAllTime",
//                    "getMostPopularGamesOfAllTime: ${result}}"
//                )

        }
        catch (e: Exception) {
            popularGamesOfAllTimeApiState = PopularGamesOfAllTimeApiState.Error
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

    fun setSearchText(text: String) {
        _gameUiState.update { currentState ->
            currentState.copy(
                searchText = text
            )
        }
    }

    fun onSearchActiveChange(active: Boolean) {
        _gameUiState.update { currentState ->
            currentState.copy(
                searchActive = active
            )
        }
    }

    fun addSearchListHistory(text: String) {
        //Check if the searchListHistory already contains the text
        if (!_gameUiState.value.searchListHistory.contains(text)) {
            _gameUiState.update { currentState ->
                currentState.copy(
                    searchListHistory = currentState.searchListHistory + text
                )
            }
        }
    }


    //**********************************************************************************************************************
    //REPOSITORY
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as GamesApplication
                val gamesRepository = application.container.gameRepository
                GameViewModel(gamesRepository)
            }
        }
    }

}
