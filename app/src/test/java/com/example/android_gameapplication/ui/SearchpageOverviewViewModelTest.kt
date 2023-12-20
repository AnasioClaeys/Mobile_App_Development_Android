package com.example.android_gameapplication.ui

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.android_gameapplication.fake.FakeApiGamesRepository
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.ui.homepage.HomepageOverviewViewModel
import com.example.android_gameapplication.ui.searchpage.SearchpageOverviewViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchpageOverviewViewModelTest {

    private lateinit var viewModel: SearchpageOverviewViewModel

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Before
    fun setup() {
        viewModel = SearchpageOverviewViewModel(FakeApiGamesRepository())
    }

    @Test
    fun SearchpageOverviewState_initialState_isEmpty() {
        // Arrange
        val searchList: List<Game> = listOf()
        val searchActive: Boolean = false
        val searchListHistory: List<String> = listOf()
        val hasSearched: Boolean = false
        var currentPage: Int = 1
        var isLoading: Boolean = false
        var lastSearchQuery: String = ""
        var isLastPage: Boolean = false


        // Act
        val gameUiState = viewModel.searchpageOverviewState.value

        // Assert
        assert(gameUiState.searchList == searchList)
        assert(gameUiState.searchActive == searchActive)
        assert(gameUiState.searchListHistory == searchListHistory)
        assert(gameUiState.hasSearched == hasSearched)
        assert(gameUiState.currentPage == currentPage)
        assert(gameUiState.isLoading == isLoading)
        assert(gameUiState.lastSearchQuery == lastSearchQuery)
        assert(gameUiState.isLastPage == isLastPage)
    }

    @Test
    fun addSearchListHistory_methodCall_searchListHistoryIsNotEmpty() {
        // Arrange
        val searchListHistory: List<String> = listOf("test")

        // Act
        viewModel.addSearchListHistory("test")

        // Assert
        assertEquals(searchListHistory, viewModel.searchpageOverviewState.value.searchListHistory)
    }

    @Test
    fun onSearchTextChange_methodCall__searchQueryIsNotEmpty() {
        // Arrange
        val searchQuery: String = "test"

        // Act
        viewModel.onSearchTextChange("test")

        // Assert
        assertEquals(searchQuery, viewModel.searchQuery.value)

    }

    @Test
    fun onSearchActiveChange_methodCall_searchActiveIsTrue() {
        // Arrange
        val searchActive: Boolean = true

        // Act
        viewModel.onSearchActiveChange(true)

        // Assert
        assertEquals(searchActive, viewModel.searchpageOverviewState.value.searchActive)
    }

    @Test
    fun performSearch_methodCall_searchQueryIsExecuted() {
        val nameGame= "Elden Ring"

        viewModel.performSearch(nameGame)

        assertEquals(nameGame, viewModel.searchpageOverviewState.value.lastSearchQuery)
    }

    @Test
    fun performSearch_methodCall_searchListIsNotEmpty() {
        val nameGame= "Elden Ring"

        viewModel.performSearch(nameGame)

        assert(viewModel.searchpageOverviewState.value.searchList.isNotEmpty())
    }

    @Test
    fun searchNextPage_methodCall_currentPageIsIncremented() {
        val nameGame= "Elden Ring"

        viewModel.performSearch(nameGame)
        viewModel.searchNextPage()

        assertEquals(2, viewModel.searchpageOverviewState.value.currentPage)
    }



}
