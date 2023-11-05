package com.example.android_gameapplication.ui.model

data class Game(
    val id: Int,
    val title: String,
    val brand: String,
    val genre: List<String>,
    val year: Int,
    val platform: List<String>
)
