package com.example.android_gameapplication.model

import com.example.android_gameapplication.data.gamesList

data class Game(
    val id: Int,
    val name: String,
//    val brand: String,
    val genres: List<String>,
    val released: String,
    val platforms: List<String>,
    val backgroundImage: String
){
    companion object {
        fun getGameById(id: Int): Game {
            return gamesList.find { it.id == id }!!
        }

        fun getAllGames(): List<Game> {
            return gamesList
        }


    }
}
