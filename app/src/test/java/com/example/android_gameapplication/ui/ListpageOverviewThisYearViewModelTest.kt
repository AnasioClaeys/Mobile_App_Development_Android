package com.example.android_gameapplication.ui

import com.example.android_gameapplication.fake.FakeApiGamesRepository
import com.example.android_gameapplication.fake.FakeDataSource
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.ui.listpagePopularGamesAllTime.ListpageOverviewAllTimeViewModel
import com.example.android_gameapplication.ui.listpagePopularGamesThisYear.ListPageOverviewThisYearViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ListpageOverviewThisYearViewModelTest {
    private lateinit var viewModel: ListPageOverviewThisYearViewModel

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Before
    fun setup(){
        viewModel = ListPageOverviewThisYearViewModel(FakeApiGamesRepository())
    }

    @Test
    fun listpageOverviewThisYearState_initialState_isCorrect() {
        var currentPageMostPlayedGamesOfThisYear = 1
        var lastPageMostPlayedGamesOfTYear = false
        val mostPlayedGamesOfThisYear: List<Game> = FakeDataSource.games

        TestCase.assertEquals(
            mostPlayedGamesOfThisYear,
            viewModel.listPageOverviewThisYearState.value.mostPlayedGamesOfThisYear
        )
        TestCase.assertEquals(
            currentPageMostPlayedGamesOfThisYear,
            viewModel.listPageOverviewThisYearState.value.currentPageMostPlayedGamesOfThisYear
        )
        TestCase.assertEquals(
            lastPageMostPlayedGamesOfTYear,
            viewModel.listPageOverviewThisYearState.value.lastPageMostPlayedGamesOfThisYear
        )
    }

    @Test
    fun loadNextPageMostPlayedGamesOfThisYear_currentPageMostPlayedGamesOfThisYear_PlusOne(){
        val currentPageMostPlayedGamesOfThisYear = 1
        val lastPageMostPlayedGamesOfThisYear = true

        viewModel.loadNextPageMostPlayedGamesOfThisYear()

        TestCase.assertEquals(
            currentPageMostPlayedGamesOfThisYear + 1,
            viewModel.listPageOverviewThisYearState.value.currentPageMostPlayedGamesOfThisYear
        )
        TestCase.assertEquals(
            lastPageMostPlayedGamesOfThisYear,
            viewModel.listPageOverviewThisYearState.value.lastPageMostPlayedGamesOfThisYear
        )
    }


}
