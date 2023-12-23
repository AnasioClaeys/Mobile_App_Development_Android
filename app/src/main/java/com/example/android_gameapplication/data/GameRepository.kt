package com.example.android_gameapplication.data

import com.example.android_gameapplication.data.database.GameDao
import com.example.android_gameapplication.data.database.asDbGame
import com.example.android_gameapplication.data.database.asDomainGame
import com.example.android_gameapplication.data.database.asDomainGames
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.ApiResponse
import com.example.android_gameapplication.network.GameApiService
import com.example.android_gameapplication.network.GameApiServiceImpl
import com.example.android_gameapplication.network.asDomainObject
import com.example.android_gameapplication.network.getMostPopularGamesOfAllTimeAsFlow
import com.example.android_gameapplication.network.getMostPopularGamesOfThisYearAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

/**
 * Interface for the repository managing game data.
 * This includes operations for fetching, inserting, and updating game data from/to local database and remote API.
 */
interface GameRepository {

    suspend fun searchGames(search: String, page: Int): ApiResponse

    suspend fun insert(game: Game)

    suspend fun getDetailGameById(id: Int): Flow<Game>

    fun getMostPopularGamesOfThisYear(): Flow<List<Game>>

    fun getMostPopularGamesOfAllTime(): Flow<List<Game>>

    suspend fun refreshMostPopularGamesOfThisYear()

    suspend fun refreshMostPopularGamesOfAllTime()

    suspend fun getMostPlayedGamesOfThisYear(page: Int): ApiResponse

    suspend fun getMostPlayedGamesOfAllTime(page: Int): ApiResponse
}

/**
 * Implementation of [GameRepository] that interacts with both local database and network API.
 *
 * @property gamesApiService Service for network API calls.
 * @property gameDao DAO for database operations.
 * @property gamesApiServiceImpl Service implementation for handling API logic.
 */
class ApiGameRepository(
    private val gamesApiService: GameApiService,
    private val gameDao: GameDao,
    private val gamesApiServiceImpl: GameApiServiceImpl,
) : GameRepository {

    override suspend fun searchGames(search: String, page: Int): ApiResponse {
        return gamesApiService.searchGames(search, pageSize = 10, page = page)
    }

    // Database
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
        } catch (e: Exception) {
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
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun getMostPlayedGamesOfThisYear(page: Int): ApiResponse {
        return gamesApiService.getMostPlayedGamesOfThisYear(page = page)
    }

    override suspend fun getMostPlayedGamesOfAllTime(page: Int): ApiResponse {
        return gamesApiService.getMostPlayedGamesOfAllTime(page = page)
    }
}
