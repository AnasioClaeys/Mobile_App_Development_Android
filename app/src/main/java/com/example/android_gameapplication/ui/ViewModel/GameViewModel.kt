package com.example.android_gameapplication.ui.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_gameapplication.data.gamesList
import com.example.android_gameapplication.ui.model.Game
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {
    private val _gameUiState = MutableStateFlow(GameUiState(gamesList= Game.getAllGames()))
    val gameUiState = _gameUiState.asStateFlow()

    //gameUiState to getGameById
    fun getGameById(gameId: Int): Game {
        return gameUiState.value.gamesList.find { it.id == gameId }!!
    }


    //**********************************************************************************************************************
    //SEARCH
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    private val _searchList = MutableStateFlow(gameUiState.value.gamesList)
    val searchList = searchText
        .combine(_searchList) { searchText, searchList ->
            if (searchText.isEmpty()) {
                searchList
            } else {
                searchList.filter { it.title.contains(searchText, ignoreCase = true) }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _searchList.value)




}
