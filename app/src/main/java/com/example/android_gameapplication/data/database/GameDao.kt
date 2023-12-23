package com.example.android_gameapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for game-related operations.
 * Provides methods to interact with the game database.
 */
@Dao
interface GameDao {
    /**
     * Inserts a game into the database. If the game already exists, it replaces the existing entry.
     *
     * @param game The [DbGame] entity to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: DbGame)

    /**
     * Retrieves a list of games marked as most popular for the current year.
     *
     * @return A [Flow] emitting a list of [DbGame] entities.
     */
    @Query("SELECT * FROM games WHERE isInMostPopularGamesOfThisYear = 1")
    fun getMostPopularGamesOfThisYear(): Flow<List<DbGame>>

    /**
     * Retrieves a list of games marked as most popular of all time.
     *
     * @return A [Flow] emitting a list of [DbGame] entities.
     */
    @Query("SELECT * FROM games WHERE isInMostPopularGamesOfAllTime = 1")
    fun getMostPopularGamesOfAllTime(): Flow<List<DbGame>>

    /**
     * Retrieves the details of a game by its ID.
     *
     * @param id The unique identifier of the game.
     * @return A [DbGame] entity or null if the game is not found.
     */
    @Query("SELECT * FROM games WHERE id = :id")
    suspend fun getDetailGameById(id: Int): DbGame?
}
