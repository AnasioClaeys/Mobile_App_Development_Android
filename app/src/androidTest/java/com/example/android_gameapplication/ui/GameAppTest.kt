package com.example.android_gameapplication.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android_gameapplication.R
import com.example.android_gameapplication.ui.layoutComponents.Destinations
import com.example.android_gameapplication.ui.layoutComponents.GameApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    private fun getResourceString(@StringRes key: Int): String {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.resources.getString(key)
    }

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            GameApp(navController, WindowWidthSizeClass.Compact)
        }
    }

    @Test
    fun startscreen_Initialize_showsHomeButton() {
        composeTestRule.onNodeWithContentDescription(
            getResourceString(R.string.bottomAppBar_navigate_to_home_screen),
            useUnmergedTree = true
        )
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun startscreen_Initialize_showsSearchButton() {
        composeTestRule.onNodeWithContentDescription(
            getResourceString(R.string.bottomAppBar_navigate_to_search_screen),
            useUnmergedTree = true
        )
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun startscreen_Initialize_showsHomeScreen() {
        composeTestRule.onNodeWithText(getResourceString(R.string.home_title_popular_games_in_YEAR))
            .assertIsDisplayed()
    }

    @Test
    fun clickOnSearchButton_navigatesToSearchScreen() {
        composeTestRule.onNodeWithContentDescription(
            getResourceString(R.string.bottomAppBar_navigate_to_search_screen),
            useUnmergedTree = true
        ).assertIsDisplayed().assertIsEnabled()
            .performClick()
        navController.currentDestination?.route?.let { route ->
            assert(route == Destinations.Search.name)
        }
    }

    @Test
    fun clickOnHomeButton_navigatesToHomeScreen() {
        composeTestRule.onNodeWithContentDescription(
            getResourceString(R.string.bottomAppBar_navigate_to_home_screen),
            useUnmergedTree = true
        )
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()
        navController.currentDestination?.route?.let { route ->
            assert(route == Destinations.Start.name)
        }
    }

    @Test
    fun clickButtonPopularGameOfThisYear_navigatesToListPagePopularGamesOfThisYear() {
        //click on button more games this year
        composeTestRule.onNodeWithText(getResourceString(R.string.more_games_this_year))
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()
        navController.currentDestination?.route?.let { route ->
            assert(route == Destinations.ListPagePopularGamesOfThisYear.name)
        }
    }

    @Test
    fun clickButtonPopularGameOfAllTime_navigatesToListPagePopularGamesAllTime() {
        // Scroll naar de knop 'more games all time'
        composeTestRule
            .onNodeWithText(getResourceString(R.string.more_games_all_time))
            .performScrollTo()

        // Klik op de knop na het scrollen
        composeTestRule
            .onNodeWithText(getResourceString(R.string.more_games_all_time))
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        // Controleer de navigatie
        navController.currentDestination?.route?.let { route ->
            assert(route == Destinations.ListPagePopularGamesAllTime.name)
        }
    }

    @Test
    fun goToSeachPage_clickOnSearchButton_OpenSearchbar_ShowsResultsFound() {
        //click on search button
        composeTestRule.onNodeWithContentDescription(
            getResourceString(R.string.bottomAppBar_navigate_to_search_screen),
            useUnmergedTree = true
        )
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        // Click on search icon to activate the search field
        composeTestRule.onNodeWithContentDescription(
            getResourceString(R.string.search_icon),
            useUnmergedTree = true
        )
            .performClick()

        // Wait for search results
        Thread.sleep(2000)

        // Check if quick results is displayed
        composeTestRule.onNodeWithText(
            getResourceString(R.string.quick_results),
            useUnmergedTree = true
        )
            .assertIsDisplayed()
    }


}
