package com.example.android_gameapplication.ui.detailpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.android_gameapplication.GamesApplication
import com.example.android_gameapplication.data.GameRepository
import com.example.android_gameapplication.network.DetailGameApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailpageOverviewViewModel(
    private val gameRepository: GameRepository,
) : ViewModel() {

    private val _detailpageOverviewState = MutableStateFlow(
        DetailpageOverviewState(
            expandedStates = mutableMapOf(),
        ),
    )
    val detailpageOverviewState = _detailpageOverviewState.asStateFlow()

    private val _gameDetailApiState =
        MutableStateFlow<DetailGameApiState>(DetailGameApiState.Loading)
    val gameDetailApiState: StateFlow<DetailGameApiState> = _gameDetailApiState.asStateFlow()

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

    fun toggleExpanded(title: String) {
        val currentState = _detailpageOverviewState.value.expandedStates[title] ?: false
        _detailpageOverviewState.value = _detailpageOverviewState.value.copy(
            expandedStates = _detailpageOverviewState.value.expandedStates.toMutableMap().apply {
                put(title, !currentState)
            },
        )
    }

    // **********************************************************************************************************************
    // REPOSITORY
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GamesApplication
                val gamesRepository = application.container.gameRepository
                DetailpageOverviewViewModel(gamesRepository)
            }
        }
    }
}
