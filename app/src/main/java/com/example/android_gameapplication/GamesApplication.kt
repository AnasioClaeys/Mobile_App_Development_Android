package com.example.android_gameapplication

import android.app.Application
import com.example.android_gameapplication.data.AppContainer
import com.example.android_gameapplication.data.DefaultAppContainer

class GamesApplication: Application() {

    lateinit var container:AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(applicationContext)
    }
}
