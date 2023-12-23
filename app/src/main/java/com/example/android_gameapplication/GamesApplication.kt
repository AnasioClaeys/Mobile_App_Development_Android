package com.example.android_gameapplication

import android.app.Application
import com.example.android_gameapplication.data.AppContainer
import com.example.android_gameapplication.data.DefaultAppContainer

/**
 * The main Application class for the Android game application.
 *
 * This class is responsible for initializing the application and setting up the application container.
 */
class GamesApplication : Application() {

    /**
     * The application container used for dependency injection.
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        
        // Initialize the application container with the application context
        container = DefaultAppContainer(applicationContext)
    }
}
