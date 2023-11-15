package com.example.android_gameapplication.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android_gameapplication.ui.layoutComponents.Destinations
import com.example.android_gameapplication.ui.layoutComponents.GameApp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GameAppTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun getResourceString(@StringRes key: Int): String {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        return context.resources.getString(key)
    }

    @Before
    fun setUp() {
        composeTestRule.setContent {
            GameApp()
        }
    }

    @Test
    fun startscreen_Initialize_showsHomeButton() {
        composeTestRule.onNodeWithContentDescription("Navigate to home screen")
            .assertIsDisplayed()
    }

//    @Test
//    fun startscreen_Initialize_showsHomeScreen() {
//        composeTestRule.onNodeWithText(getResourceString())
//            .assertIsDisplayed()
//    }
}
