package com.example.android_gameapplication.ui

import com.example.android_gameapplication.fake.FakeApiGamesRepository
import com.example.android_gameapplication.fake.FakeDataSource
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.ui.listpagePopularGamesAllTime.ListpageOverviewAllTimeViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListpageOverviewAllTimeViewModelTest {
    private lateinit var viewModel: ListpageOverviewAllTimeViewModel

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Before
    fun setup(){
        viewModel = ListpageOverviewAllTimeViewModel(FakeApiGamesRepository())
    }

    @Test
    fun listpageOverviewAllTimeState_initialState_isCorrect() {
        var currentPageMostPlayedGamesOfAllTime = 1
        var lastPageMostPlayedGamesOfTAllTime = false
        val mostPlayedGamesOfAllTime: List<Game> = FakeDataSource.games

        assertEquals(mostPlayedGamesOfAllTime, viewModel.listpageOverviewAllTimeState.value.mostPlayedGamesOfAllTime)
        assertEquals(currentPageMostPlayedGamesOfAllTime, viewModel.listpageOverviewAllTimeState.value.currentPageMostPlayedGamesOfAllTime)
        assertEquals(lastPageMostPlayedGamesOfTAllTime, viewModel.listpageOverviewAllTimeState.value.lastPageMostPlayedGamesOfTAllTime)
    }

    @Test
    fun loadNextPageMostPlayedGamesOfAllTime_currentPageMostPlayedGamesOfAllTime_PlusOne(){
        val currentPageMostPlayedGamesOfAllTime = 1
        val lastPageMostPlayedGamesOfTAllTime = true

        viewModel.loadNextPageMostPlayedGamesOfAllTime()

        assertEquals(currentPageMostPlayedGamesOfAllTime + 1, viewModel.listpageOverviewAllTimeState.value.currentPageMostPlayedGamesOfAllTime)
        assertEquals(lastPageMostPlayedGamesOfTAllTime, viewModel.listpageOverviewAllTimeState.value.lastPageMostPlayedGamesOfTAllTime)
    }


}
