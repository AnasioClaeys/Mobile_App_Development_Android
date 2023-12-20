package com.example.android_gameapplication.ui

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.android_gameapplication.fake.FakeApiGamesRepository
import com.example.android_gameapplication.fake.FakeDataSource
import com.example.android_gameapplication.network.PopularGamesOfAllTimeApiState
import com.example.android_gameapplication.network.PopularGamesOfThisYearApiState
import com.example.android_gameapplication.ui.homepage.HomepageOverviewViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class HomepageOverviewViewModelTest {
    private lateinit var viewModel: HomepageOverviewViewModel

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Before
    fun setup(){
        viewModel = HomepageOverviewViewModel(FakeApiGamesRepository())
    }

    @Test
    fun getMostPopularGamesOfThisYear_methodCall_StateIsSuccessAfterCall(){
        // Arrange
        val expectedState = PopularGamesOfThisYearApiState.Success

        // Act
        viewModel.getMostPopularGamesOfThisYear()

        // Assert
        assertEquals(expectedState, viewModel.popularGamesOfThisYearApiState)
    }

    @Test
    fun getMostPopularGamesOfAllTime_methodCall_StateIsSuccessAfterCall(){
        // Arrange
        val expectedState = PopularGamesOfAllTimeApiState.Success

        // Act
        viewModel.getMostPopularGamesOfAllTime()

        // Assert
        assertEquals(expectedState, viewModel.popularGamesOfAllTimeApiState)
    }

}
