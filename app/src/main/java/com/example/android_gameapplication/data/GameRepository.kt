package com.example.android_gameapplication.data

import android.util.Log
import androidx.room.Query
import com.example.android_gameapplication.data.database.DbGame
import com.example.android_gameapplication.data.database.GameDao
import com.example.android_gameapplication.data.database.asDbGame
import com.example.android_gameapplication.data.database.asDomainGame
import com.example.android_gameapplication.data.database.asDomainGames
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.ApiResponse
import com.example.android_gameapplication.network.GameApiService
import com.example.android_gameapplication.network.GameApiServiceImpl
import com.example.android_gameapplication.network.asDomainObject
import com.example.android_gameapplication.network.asDomainObjects
import com.example.android_gameapplication.network.getMostPopularGamesOfAllTimeAsFlow
import com.example.android_gameapplication.network.getMostPopularGamesOfThisYearAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface GameRepository {
    suspend fun getGames(): List<Game>

    //    suspend fun getMostPopularGamesOfThisYear(): List<Game>
//    suspend fun getMostPopularGamesOfAllTime(): List<Game>

    suspend fun searchGames(search: String, page:Int): ApiResponse

    suspend fun insert(game: Game)

    suspend fun getDetailGameById(id: Int): Flow<Game>

    fun getMostPopularGamesOfThisYear(): Flow<List<Game>>

    fun getMostPopularGamesOfAllTime(): Flow<List<Game>>

    suspend fun refreshMostPopularGamesOfThisYear()

    suspend fun refreshMostPopularGamesOfAllTime()

}

class ApiGameRepository(
    private val gamesApiService: GameApiService,
    private val gameDao: GameDao,
    private val gamesApiServiceImpl: GameApiServiceImpl
) : GameRepository {

    //API
    override suspend fun getGames(): List<Game> {
        return gamesApiService.getGames().asDomainObjects()
    }

    override suspend fun searchGames(search: String, page:Int): ApiResponse {
        return gamesApiService.searchGames(search, pageSize = 10, page=page)
    }

    //Database
    override suspend fun getDetailGameById(id: Int): Flow<Game> = flow {
        val localGame = gameDao.getDetailGameById(id)

        if (localGame != null) {
            emit(localGame.asDomainGame())
        } else {
            val apiGame = gamesApiService.getGameDetailById(id)
            insert(apiGame.asDomainObject())
            emit(apiGame.asDomainObject())
        }
    }




    override suspend fun insert(game: Game) {
        gameDao.insert(game.asDbGame())
    }

    override fun getMostPopularGamesOfThisYear(): Flow<List<Game>> {
        return gameDao.getMostPopularGamesOfThisYear().map {
            it.asDomainGames()
        }
    }

    override fun getMostPopularGamesOfAllTime(): Flow<List<Game>> {
        return gameDao.getMostPopularGamesOfAllTime().map {
            it.asDomainGames()
        }
    }

    override suspend fun refreshMostPopularGamesOfThisYear() {
        try {
            gamesApiServiceImpl.getMostPopularGamesOfThisYearAsFlow().collect {
                for (game in it.results) {
                    insert(game.asDomainObject())
                }
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun refreshMostPopularGamesOfAllTime() {
        try {
            gamesApiServiceImpl.getMostPopularGamesOfAllTimeAsFlow().collect {
                for (game in it.results) {
                    insert(game.asDomainObject())
                }
            }
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
