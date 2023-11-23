package com.example.android_gameapplication.data

import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.GameApiService
import com.example.android_gameapplication.network.asDomainObject
import com.example.android_gameapplication.network.asDomainObjects

interface GamesRepository {
    suspend fun getGames(): List<Game>
    suspend fun getMostPopularGamesOfThisYear(): List<Game>
    suspend fun getMostPopularGamesOfAllTime(): List<Game>
    suspend fun getDetailGameById(id:Int): Game

}

class ApiGamesRepository(
    private val gamesApiService: GameApiService
) : GamesRepository {
    override suspend fun getGames(): List<Game> {
        return gamesApiService.getGames().asDomainObjects()
    }

    override suspend fun getMostPopularGamesOfThisYear(): List<Game> {
        return gamesApiService.getMostPopularGamesOfThisYear().asDomainObjects()
    }

    override suspend fun getMostPopularGamesOfAllTime(): List<Game> {
        return gamesApiService.getMostPopularGamesOfAllTime().asDomainObjects()
    }

    override suspend fun getDetailGameById(id: Int): Game {
        return gamesApiService.getGameDetailById(id).asDomainObject()
    }
}
