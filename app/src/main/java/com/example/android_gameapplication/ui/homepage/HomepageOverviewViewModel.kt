package com.example.android_gameapplication.ui.homepage

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
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.PopularGamesOfAllTimeApiState
import com.example.android_gameapplication.network.PopularGamesOfThisYearApiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * ViewModel for managing the homepage's UI state and fetching data for the most popular games of the year and all time.
 *
 * @property gameRepository The repository for accessing game data.
 */
class HomepageOverviewViewModel(private val gameRepository: GameRepository) : ViewModel() {

    var popularGamesOfThisYearApiState: PopularGamesOfThisYearApiState by mutableStateOf(
        PopularGamesOfThisYearApiState.Loading,
    )
        private set

    var popularGamesOfAllTimeApiState: PopularGamesOfAllTimeApiState by mutableStateOf(
        PopularGamesOfAllTimeApiState.Loading,
    )
        private set

    lateinit var uiListPopularGamesOfThisYearState: StateFlow<List<Game>>

    lateinit var uiListPopularGamesOfAllTimeState: StateFlow<List<Game>>

    init {
        getMostPopularGamesOfThisYear()
        getMostPopularGamesOfAllTime()
    }

    /**
     * Fetches and updates the UI state for the most popular games of the current year.
     */
    fun getMostPopularGamesOfThisYear() {
        try {
            viewModelScope.launch {
                gameRepository.refreshMostPopularGamesOfThisYear()
            }

            // Ophalen van de data
            uiListPopularGamesOfThisYearState =
                gameRepository.getMostPopularGamesOfThisYear().stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = listOf(),
                )

            // Update de PopularGamesOfThisYearApiState
            popularGamesOfThisYearApiState =
                PopularGamesOfThisYearApiState.Success
        } catch (e: Exception) {
            popularGamesOfThisYearApiState = PopularGamesOfThisYearApiState.Error
        }
    }

    /**
     * Fetches and updates the UI state for the most popular games of all time.
     */
    fun getMostPopularGamesOfAllTime() {
        try {
            viewModelScope.launch {
                gameRepository.refreshMostPopularGamesOfAllTime()
            }

            uiListPopularGamesOfAllTimeState =
                gameRepository.getMostPopularGamesOfAllTime().stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = listOf(),
                )

            // Update de PopularGamesOfAllTimeApiState
            popularGamesOfAllTimeApiState =
                PopularGamesOfAllTimeApiState.Success
        } catch (e: Exception) {
            popularGamesOfAllTimeApiState = PopularGamesOfAllTimeApiState.Error
        }
    }

    // Factory for creating instances of the ViewModel.
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GamesApplication
                val gamesRepository = application.container.gameRepository
                HomepageOverviewViewModel(gamesRepository)
            }
        }
    }
}
