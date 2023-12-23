package com.example.android_gameapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.example.android_gameapplication.ui.layoutComponents.GameApp
import com.example.android_gameapplication.ui.theme.Android_GameApplicationTheme

/**
 * The main activity for the Android game application.
 *
 * This activity serves as the entry point for the application and sets up the Compose UI.
 */
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is created.
     *
     * @param savedInstanceState The saved state of the activity, if any.
     */
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Set the content of the activity using Compose
            Android_GameApplicationTheme {

                // Create a Surface that fills the entire screen
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    // Calculate the window size class for adaptive UI
                    val windowSize = calculateWindowSizeClass(this)

                    // Load the main GameApp Composable with the calculated window size
                    GameApp(windowSize = windowSize.widthSizeClass)
                }
            }
        }
    }
}
