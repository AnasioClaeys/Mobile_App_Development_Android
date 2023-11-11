package com.example.android_gameapplication.ui.model

import com.example.android_gameapplication.data.gamesList

data class Game(
    val id: Int,
    val title: String,
    val brand: String,
    val genre: List<String>,
    val year: Int,
    val platform: List<String>
){
    companion object {
        fun getGameById(id: Int): Game {
            return gamesList.find { it.id == id }!!
        }
    }
}
