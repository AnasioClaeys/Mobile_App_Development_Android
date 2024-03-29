package com.example.android_gameapplication.ui.searchpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.android_gameapplication.GamesApplication
import com.example.android_gameapplication.data.GameRepository
import com.example.android_gameapplication.network.SearchGameApiState
import com.example.android_gameapplication.network.asDomainObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel class responsible for managing the state and logic of the search page in the Android game application.
 *
 * @param gameRepository The repository for accessing game data.
 */
class SearchpageOverviewViewModel(
    private val gameRepository: GameRepository,
) : ViewModel() {

    // Mutable state flow for holding the search page state
    private val _searchpageOverviewState = MutableStateFlow(
        SearchpageOverviewState(
            searchList = emptyList(),
            searchActive = false,
            searchListHistory = emptyList(),
            hasSearched = false,
            currentPage = 1,
            isLoading = false,
        ),
    )
    val searchpageOverviewState = _searchpageOverviewState.asStateFlow()

    /**
     * Loads the next page of search results.
     */
    fun searchNextPage() {
        if (!_searchpageOverviewState.value.isLoading && !_searchpageOverviewState.value.isLastPage) {
            viewModelScope.launch {
                _searchpageOverviewState.value.isLoading = true
                try {
                    val result = gameRepository.searchGames(
                        _searchpageOverviewState.value.lastSearchQuery,
                        _searchpageOverviewState.value.currentPage + 1,
                    )
                    _searchpageOverviewState.value.currentPage =
                        _searchpageOverviewState.value.currentPage + 1

                    _searchpageOverviewState.value.isLastPage =
                        (result.next.isNullOrEmpty() || result.count <= result.results.size)

                    _searchpageOverviewState.update { currentState ->
                        currentState.copy(
                            searchList = currentState.searchList + result.asDomainObjects(),
                        )
                    }
                } catch (e: Exception) {
                    // Foutafhandeling
                } finally {
                    _searchpageOverviewState.value.isLoading = false
                }
            }
        }
    }

    // Mutable state flow for holding the current search query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        // Perform a search when the search query changes, debounce to avoid rapid searches
        viewModelScope.launch {
            _searchQuery
                .debounce(200)
                .distinctUntilChanged()
                .collect { query ->
                    performSearch(query)
                }
        }
    }

    // Mutable state flow for holding the state of the search API call
    private val _searchGameApiState =
        MutableStateFlow<SearchGameApiState>(SearchGameApiState.Loading)
    val searchGameApiState: StateFlow<SearchGameApiState> = _searchGameApiState.asStateFlow()

    /**
     * Performs a search for games based on the provided query.
     *
     * @param query The search query.
     */
    fun performSearch(query: String) {
        _searchpageOverviewState.value.lastSearchQuery = query
        _searchpageOverviewState.value.currentPage = 1
        viewModelScope.launch {
            try {
                _searchGameApiState.value = SearchGameApiState.Loading
                val result = gameRepository.searchGames(
                    _searchpageOverviewState.value.lastSearchQuery,
                    _searchpageOverviewState.value.currentPage,
                )
                _searchpageOverviewState.update { currentState ->
                    currentState.copy(
                        searchList = result.asDomainObjects(),
                        hasSearched = true,
                    )
                }
                _searchGameApiState.value = SearchGameApiState.Success(result.asDomainObjects())
            } catch (e: Exception) {
                _searchGameApiState.value = SearchGameApiState.Error
                _searchpageOverviewState.update { currentState ->
                    currentState.copy(
                        searchList = emptyList(),
                        hasSearched = true,
                    )
                }
            }
        }
    }

    /**
     * Updates the search query with the provided text.
     *
     * @param text The new search query text.
     */
    fun onSearchTextChange(text: String) {
        _searchQuery.value = text
    }

    /**
     * Updates the search active state.
     *
     * @param active The new search active state.
     */
    fun onSearchActiveChange(active: Boolean) {
        _searchpageOverviewState.update { currentState ->
            currentState.copy(
                searchActive = active,
            )
        }
    }

    /**
     * Updates the search active state.
     *
     * @param active The new search active state.
     */
    fun addSearchListHistory(text: String) {
        if (!_searchpageOverviewState.value.searchListHistory.contains(text)) {
            _searchpageOverviewState.update { currentState ->
                currentState.copy(
                    searchListHistory = currentState.searchListHistory + text,
                )
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
                SearchpageOverviewViewModel(gamesRepository)
            }
        }
    }
}
