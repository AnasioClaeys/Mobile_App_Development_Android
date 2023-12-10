package com.example.android_gameapplication.data

import android.util.Log
import androidx.room.Query
import com.example.android_gameapplication.data.database.DbGame
import com.example.android_gameapplication.data.database.GameDao
import com.example.android_gameapplication.data.database.asDbGame
import com.example.android_gameapplication.data.database.asDomainGame
import com.example.android_gameapplication.data.database.asDomainGames
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.GameApiService
import com.example.android_gameapplication.network.asDomainObject
import com.example.android_gameapplication.network.asDomainObjects
import com.example.android_gameapplication.network.getGamesAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface GameRepository {
    suspend fun getGames(): List<Game>

    //    suspend fun getMostPopularGamesOfThisYear(): List<Game>
//    suspend fun getMostPopularGamesOfAllTime(): List<Game>

    suspend fun insert(game: Game)
    suspend fun getDetailGameById(id: Int): Game

    fun getMostPopularGamesOfThisYear(): Flow<List<Game>>

    fun getMostPopularGamesOfAllTime(): Flow<List<Game>>

    suspend fun refresh()

}

class ApiGameRepository(
    private val gamesApiService: GameApiService,
    private val gameDao: GameDao
) : GameRepository {

    //API
    override suspend fun getGames(): List<Game> {
        return gamesApiService.getGames().asDomainObjects()
    }

//    override suspend fun getMostPopularGamesOfThisYear(): List<Game> {
//        return gamesApiService.getMostPopularGamesOfThisYear().asDomainObjects()
//    }
//
//    override suspend fun getMostPopularGamesOfAllTime(): List<Game> {
//        return gamesApiService.getMostPopularGamesOfAllTime().asDomainObjects()
//    }

    override suspend fun getDetailGameById(id: Int): Game {
        return gamesApiService.getGameDetailById(id).asDomainObject()
    }


    //Database

    override suspend fun insert(game: Game) {
        val isInMostPopularGamesOfThisYear = gamesApiService.getMostPopularGamesOfThisYear()
            .results.any { it.id == game.id }

        val isInMostPopularGamesOfAllTime = gamesApiService.getMostPopularGamesOfAllTime()
            .results.any { it.id == game.id }

        val dbGame = game.asDbGame().copy(
            isInMostPopularGamesOfThisYear = isInMostPopularGamesOfThisYear,
            isInMostPopularGamesOfAllTime = isInMostPopularGamesOfAllTime
        )

        gameDao.insert(dbGame)


    }

    override fun getMostPopularGamesOfThisYear(): Flow<List<Game>> {
        return gameDao.getMostPopularGamesOfThisYear().map {
            it.asDomainGames()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override fun getMostPopularGamesOfAllTime(): Flow<List<Game>> {
        return gameDao.getMostPopularGamesOfAllTime().map {
            it.asDomainGames()
        }.onEach {
            if (it.isEmpty()) {
                refresh()
            }
        }
    }

    override suspend fun refresh() {
        gamesApiService.getGamesAsFlow().collect {
            for (game in it.results) {
                Log.i("TEST", "refresh: ${game.asDomainObject()}")

                insert(game.asDomainObject())
            }
        }
    }

}
