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
import com.example.android_gameapplication.network.SearchGameApiState
import com.example.android_gameapplication.network.asDomainObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
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

    //api

    var gameApiState: GameApiState by mutableStateOf(GameApiState.Loading)
        private set

//    var gameDetailApiState: DetailGameApiState by mutableStateOf(DetailGameApiState.Loading)
//        private set

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
        Log.d("GameViewModel", "Initializing GameViewModel")
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

    // Nieuwe StateFlow voor detail game status
    private val _gameDetailApiState = MutableStateFlow<DetailGameApiState>(DetailGameApiState.Loading)
    val gameDetailApiState: StateFlow<DetailGameApiState> = _gameDetailApiState.asStateFlow()

    // Functie om detail game data op te halen
    fun getDetailGameById(gameId: Int) {
        viewModelScope.launch {
            try {
                _gameDetailApiState.value = DetailGameApiState.Loading
                gameRepository.getDetailGameById(gameId).collect { game ->
                    _gameDetailApiState.value = DetailGameApiState.Success(game)
                }
            } catch (e: Exception) {
                _gameDetailApiState.value = DetailGameApiState.Error
            }
        }
    }




        private fun getMostPopularGamesOfThisYear() {
            try {
                viewModelScope.launch {
                    gameRepository.refreshMostPopularGamesOfThisYear()
                }

                //Ophalen van de data
//                val result = gameRepository.getMostPopularGamesOfThisYear()
                uiListPopularGamesOfThisYearState =
                    gameRepository.getMostPopularGamesOfThisYear().stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
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
                    gameRepository.refreshMostPopularGamesOfAllTime()
                }

                //Ophalen van de data
//                val result = gameRepository.getMostPopularGamesOfAllTime()
                uiListPopularGamesOfAllTimeState =
                    gameRepository.getMostPopularGamesOfAllTime().stateIn(
                        scope = viewModelScope,
                        started = SharingStarted.WhileSubscribed(5_000L),
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

            } catch (e: Exception) {
                popularGamesOfAllTimeApiState = PopularGamesOfAllTimeApiState.Error
            }
        }

        //**********************************************************************************************************************
        //SEARCH

    private var currentPage = 1
    private var isLoading = false
    private var lastSearchQuery = ""
    var isLastPage = false

    fun searchNextPage() {
        if (!isLoading && !isLastPage) {
            viewModelScope.launch {
                isLoading = true
                try {
                    val result = gameRepository.searchGames(lastSearchQuery, currentPage + 1)
                    currentPage++

                    // Check of dit de laatste pagina is
                    isLastPage = (result.next.isNullOrEmpty() || result.count<=result.results.size)

                    // Update de UI State hier met de nieuwe games
                    // Voeg de nieuwe games toe aan de bestaande lijst
                    _gameUiState.update { currentState ->
                        currentState.copy(
                            searchList = currentState.searchList + result.asDomainObjects()
                        )
                    }
                } catch (e: Exception) {
                    // Foutafhandeling
                } finally {
                    isLoading = false
                }
            }
        }
    }



        private val _searchQuery = MutableStateFlow("")
        val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

        init {
            viewModelScope.launch {
                _searchQuery
                    .debounce(200) // Debounce tijd in milliseconden
                    .distinctUntilChanged() // Voer alleen uit als de query verandert
                    .collect { query ->
                        performSearch(query)
                    }
            }
        }
    private val _searchGameApiState = MutableStateFlow<SearchGameApiState>(SearchGameApiState.Loading)
    val searchGameApiState: StateFlow<SearchGameApiState> = _searchGameApiState.asStateFlow()

    private fun performSearch(query: String) {
        lastSearchQuery = query
        currentPage = 1
        viewModelScope.launch {
            try {
                _searchGameApiState.value = SearchGameApiState.Loading
                val result = gameRepository.searchGames(lastSearchQuery, currentPage)
                _gameUiState.update { currentState ->
                    currentState.copy(
                        searchList = result.asDomainObjects(),
                        hasSearched = true
                    )
                }
                _searchGameApiState.value = SearchGameApiState.Success(result.asDomainObjects())
            } catch (e: Exception) {
                _searchGameApiState.value = SearchGameApiState.Error
                _gameUiState.update { currentState ->
                    currentState.copy(
                        searchList = emptyList(),
                        hasSearched = true
                    )
                }
            }
        }
    }

        fun onSearchTextChange(text: String) {
            _searchQuery.value = text
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
