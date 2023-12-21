package com.example.android_gameapplication.ui

import com.example.android_gameapplication.fake.FakeApiGamesRepository
import com.example.android_gameapplication.fake.FakeDataSource
import com.example.android_gameapplication.ui.detailpage.DetailpageOverviewViewModel
import com.example.android_gameapplication.ui.homepage.HomepageOverviewViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailpageOverviewViewModelTest {

    private lateinit var viewModel: DetailpageOverviewViewModel

    @get:Rule
    val testDispatcher = TestDispatchersRule()

    @Before
    fun setup(){
        viewModel = DetailpageOverviewViewModel(FakeApiGamesRepository())
    }

    @Test
    fun getDetailGameById_runs() {
        viewModel.getDetailGameById(1)
    }

    @Test
    fun initialState_emptyExpandedStates() {
        assertTrue("Initial state of expandedStates should be empty", viewModel.detailpageOverviewState.value.expandedStates.isEmpty())
    }

    @Test
    fun toggleExpanded_newTitle_expands() {
        val testTitle = "TestTitle"
        viewModel.toggleExpanded(testTitle)
        assertTrue("toggleExpanded should set new title to true", viewModel.detailpageOverviewState.value.expandedStates[testTitle] == true)
    }

    @Test
    fun toggleExpanded_existingTitle_togglesState()  {
        val testTitle = "ExistingTitle"
        viewModel.toggleExpanded(testTitle) // Sets to true
        viewModel.toggleExpanded(testTitle) // Should toggle to false

        assertFalse("toggleExpanded should toggle existing title's state", viewModel.detailpageOverviewState.value.expandedStates[testTitle] == true)
    }


}
