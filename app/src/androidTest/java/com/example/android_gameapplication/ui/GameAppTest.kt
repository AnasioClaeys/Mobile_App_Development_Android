package com.example.android_gameapplication.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
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
            GameApp(navController)
        }
    }

    @Test
    fun startscreen_Initialize_showsHomeButton() {
        composeTestRule.onNodeWithContentDescription(getResourceString(R.string.bottomAppBar_navigate_to_home_screen))
            .assertIsDisplayed()
            .assertIsEnabled()
    }

    @Test
    fun startscreen_Initialize_showsSearchButton() {
        composeTestRule.onNodeWithContentDescription(getResourceString(R.string.bottomAppBar_navigate_to_search_screen))
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
        composeTestRule.onNodeWithContentDescription(getResourceString(R.string.bottomAppBar_navigate_to_search_screen))
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()
        navController.currentDestination?.route?.let { route ->
            assert(route == Destinations.Search.name)
        }
    }

    @Test
    fun clickOnHomeButton_navigatesToHomeScreen() {
        composeTestRule.onNodeWithContentDescription(getResourceString(R.string.bottomAppBar_navigate_to_home_screen))
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()
        navController.currentDestination?.route?.let { route ->
            assert(route == Destinations.Start.name)
        }
    }

    @Test
    fun clickButtonPopularGameOfThisYear_navigatesToListPagePopularGamesOfThisYear(){
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
    fun clickButtonPopularGameOfAllTime_navigatesToListPagePopularGamesAllTime(){
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
    fun goToSeachPage_clickOnSearchButton_TypesTextThatIsnTInList_ShowsNoResultsFound(){
        //click on search button
        composeTestRule.onNodeWithContentDescription(getResourceString(R.string.bottomAppBar_navigate_to_search_screen))
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        //type text that isn't in list
        composeTestRule.onNodeWithContentDescription(getResourceString(R.string.search_placeholder))
            .performTextInput("ZAAAAAAAAAAAAAAAAAAAAAAAZZZZZZZZZZZZZZZZZZAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")

        //click on search button in keyboard
        composeTestRule.onNodeWithContentDescription(getResourceString(R.string.search_placeholder))
            .performImeAction()

        //check if text no results found is displayed
        composeTestRule.onNodeWithText(getResourceString(R.string.no_games_found))
            .assertIsDisplayed()
    }

    @Test
    fun goToSeachPage_clickOnSearchButton_TypesTextThatIsInList_ShowsResultsFound(){
        //click on search button
        composeTestRule.onNodeWithContentDescription(getResourceString(R.string.bottomAppBar_navigate_to_search_screen))
            .assertIsDisplayed()
            .assertIsEnabled()
            .performClick()

        //type text that isn't in list
        composeTestRule.onNodeWithContentDescription(getResourceString(R.string.search_placeholder))
            .performTextInput("Grand")

    //wait for search results
        Thread.sleep(1000)


            //check if quick results is displayed
        composeTestRule.onNodeWithText(getResourceString(R.string.quick_results))
            .assertIsDisplayed()

    }



}
