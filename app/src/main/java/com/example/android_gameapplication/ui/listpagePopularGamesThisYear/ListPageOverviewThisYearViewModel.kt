package com.example.android_gameapplication.ui.listpagePopularGamesThisYear

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
import com.example.android_gameapplication.network.MostPlayedGamesOfThisYearApiState
import com.example.android_gameapplication.network.asDomainObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ListPageOverviewThisYearViewModel(
    private val gameRepository: GameRepository,
) : ViewModel() {

    private val _listPageOverviewThisYearState = MutableStateFlow(
        ListPageOverviewThisYearState(
            mostPlayedGamesOfThisYear = emptyList(),
        ),
    )
    val listPageOverviewThisYearState = _listPageOverviewThisYearState.asStateFlow()

    var mostPlayedGamesOfThisYearApiState: MostPlayedGamesOfThisYearApiState by mutableStateOf(
        MostPlayedGamesOfThisYearApiState.Loading,
    )
        private set

    init {
        getMostPlayedGamesOfThisYear()
    }

    private fun getMostPlayedGamesOfThisYear() {
        _listPageOverviewThisYearState.value.currentPageMostPlayedGamesOfThisYear = 1
        viewModelScope.launch {
            try {
                val result =
                    gameRepository.getMostPlayedGamesOfThisYear(_listPageOverviewThisYearState.value.currentPageMostPlayedGamesOfThisYear)

                _listPageOverviewThisYearState.update { currentState ->
                    currentState.copy(
                        mostPlayedGamesOfThisYear = result.asDomainObjects(),
                    )
                }
                mostPlayedGamesOfThisYearApiState =
                    MostPlayedGamesOfThisYearApiState.Success
            } catch (e: Exception) {
                mostPlayedGamesOfThisYearApiState =
                    MostPlayedGamesOfThisYearApiState.Error
                _listPageOverviewThisYearState.update { currentState ->
                    currentState.copy(
                        mostPlayedGamesOfThisYear = emptyList(),
                    )
                }
            }
        }
    }

    fun loadNextPageMostPlayedGamesOfThisYear() {
        if (mostPlayedGamesOfThisYearApiState != MostPlayedGamesOfThisYearApiState.Loading && !_listPageOverviewThisYearState.value.lastPageMostPlayedGamesOfThisYear) {
            viewModelScope.launch {
                try {
                    val result =
                        gameRepository.getMostPlayedGamesOfThisYear(_listPageOverviewThisYearState.value.currentPageMostPlayedGamesOfThisYear + 1)

                    val currentGames =
                        _listPageOverviewThisYearState.value.mostPlayedGamesOfThisYear
                    val updatedGames = currentGames + result.asDomainObjects()

                    _listPageOverviewThisYearState.update { currentState ->
                        currentState.copy(mostPlayedGamesOfThisYear = updatedGames)
                    }
                    _listPageOverviewThisYearState.value.currentPageMostPlayedGamesOfThisYear++

                    _listPageOverviewThisYearState.value.lastPageMostPlayedGamesOfThisYear =
                        result.next.isNullOrEmpty() || result.count <= result.results.size
                    mostPlayedGamesOfThisYearApiState = MostPlayedGamesOfThisYearApiState.Success
                } catch (e: Exception) {
                    mostPlayedGamesOfThisYearApiState = MostPlayedGamesOfThisYearApiState.Error
                }
            }
        }
    }

    // **********************************************************************************************************************
    // REPOSITORY
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GamesApplication
                val gamesRepository = application.container.gameRepository
                ListPageOverviewThisYearViewModel(gamesRepository)
            }
        }
    }
}
