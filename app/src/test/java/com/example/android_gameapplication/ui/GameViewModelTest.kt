package com.example.android_gameapplication.ui

import com.example.android_gameapplication.ui.ViewModel.GameViewModel
import com.example.android_gameapplication.model.Game
import junit.framework.TestCase.assertEquals
import org.junit.Test


class GameViewModelTest {
    private val viewModel = GameViewModel(gamesRepository)
    private val TEXT = "text"



    @Test
    fun gameViewModel_Initialization_SearchTextIsEmpty(){
        assertEquals("", viewModel.gameUiState.value?.searchText)
    }



    @Test
    fun gameViewModel_onSearchTextChange_SearchTextIsEqualToText(){
        viewModel.onSearchTextChange(TEXT)
        assertEquals(TEXT, viewModel.gameUiState.value?.searchText)
    }


}
