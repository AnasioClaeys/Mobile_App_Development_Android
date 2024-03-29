package com.example.android_gameapplication.ui.listpagePopularGamesAllTime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.android_gameapplication.GamesApplication
import com.example.android_gameapplication.data.GameRepository
import com.example.android_gameapplication.network.MostPlayedGamesOfAllTimeApiState
import com.example.android_gameapplication.network.asDomainObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the state and fetching data for the list of most played games of all time.
 * It holds and updates the list of games and pagination details.
 *
 * @property gameRepository The repository for accessing game data.
 */
class ListpageOverviewAllTimeViewModel(
    private val gameRepository: GameRepository,
) : ViewModel() {

    private val _listpageOverviewAllTimeState = MutableStateFlow(
        ListpageOverviewAllTimeState(
            mostPlayedGamesOfAllTime = emptyList(),
            currentPageMostPlayedGamesOfAllTime = 1,
            lastPageMostPlayedGamesOfTAllTime = false,
        ),
    )
    val listpageOverviewAllTimeState = _listpageOverviewAllTimeState.asStateFlow()

    var mostPlayedGamesOfAllTimeApiState: MostPlayedGamesOfAllTimeApiState by mutableStateOf(
        MostPlayedGamesOfAllTimeApiState.Loading,
    )
        private set

    init {
        getMostPlayedGamesOfAllTime()
    }

    /**
     * Fetches and updates the UI state for the most played games of all time.
     */
    private fun getMostPlayedGamesOfAllTime() {
        listpageOverviewAllTimeState.value.currentPageMostPlayedGamesOfAllTime = 1
        viewModelScope.launch {
            try {
                // Ophalen van de data
                val result =
                    gameRepository.getMostPlayedGamesOfAllTime(listpageOverviewAllTimeState.value.currentPageMostPlayedGamesOfAllTime)

                // Update de gameApiState
                _listpageOverviewAllTimeState.update { currentState ->
                    currentState.copy(
                        mostPlayedGamesOfAllTime = result.asDomainObjects(),
                    )
                }
                mostPlayedGamesOfAllTimeApiState =
                    MostPlayedGamesOfAllTimeApiState.Success
            } catch (e: Exception) {
                mostPlayedGamesOfAllTimeApiState =
                    MostPlayedGamesOfAllTimeApiState.Error
                _listpageOverviewAllTimeState.update { currentState ->
                    currentState.copy(
                        mostPlayedGamesOfAllTime = emptyList(),
                    )
                }
            }
        }
    }

    /**
     * Loads the next page of the most played games of all time.
     */
    fun loadNextPageMostPlayedGamesOfAllTime() {
        if (mostPlayedGamesOfAllTimeApiState != MostPlayedGamesOfAllTimeApiState.Loading && !listpageOverviewAllTimeState.value.lastPageMostPlayedGamesOfTAllTime) {
            viewModelScope.launch {
                try {
                    val result =
                        gameRepository.getMostPlayedGamesOfAllTime(listpageOverviewAllTimeState.value.currentPageMostPlayedGamesOfAllTime + 1)

                    // Voeg de nieuwe games toe aan de bestaande lijst
                    val currentGames = _listpageOverviewAllTimeState.value.mostPlayedGamesOfAllTime
                    val updatedGames = currentGames + result.asDomainObjects()

                    _listpageOverviewAllTimeState.update { currentState ->
                        currentState.copy(mostPlayedGamesOfAllTime = updatedGames)
                    }
                    listpageOverviewAllTimeState.value.currentPageMostPlayedGamesOfAllTime++

                    // Update lastPageMostPlayedGamesOfThisYear
                    listpageOverviewAllTimeState.value.lastPageMostPlayedGamesOfTAllTime =
                        result.next.isNullOrEmpty() || result.count <= result.results.size
                    mostPlayedGamesOfAllTimeApiState = MostPlayedGamesOfAllTimeApiState.Success
                } catch (e: Exception) {
                    mostPlayedGamesOfAllTimeApiState = MostPlayedGamesOfAllTimeApiState.Error
                }
            }
        }
    }

    // Factory for creating instances of the ViewModel.
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GamesApplication
                val gamesRepository = application.container.gameRepository
                ListpageOverviewAllTimeViewModel(gamesRepository)
            }
        }
    }
}
