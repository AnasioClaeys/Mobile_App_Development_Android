package com.example.android_gameapplication.ui

import com.example.android_gameapplication.fake.FakeApiGamesRepository
import com.example.android_gameapplication.fake.FakeDataSource
import com.example.android_gameapplication.ui.detailpage.DetailpageOverviewViewModel
import com.example.android_gameapplication.ui.homepage.HomepageOverviewViewModel
import junit.framework.TestCase.assertEquals
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
}
