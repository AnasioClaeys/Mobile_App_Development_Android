package com.example.android_gameapplication.model

import com.example.android_gameapplication.data.gamesList

data class Game(
    val id: Int,
    val name: String,
    val genres: List<String>,
    val released: String,
    val platforms: List<String>,
    val backgroundImage: String
)
