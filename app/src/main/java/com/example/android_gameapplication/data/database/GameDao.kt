package com.example.android_gameapplication.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(game: DbGame)

    @Query("SELECT * FROM games WHERE isInMostPopularGamesOfThisYear = 1")
    fun getMostPopularGamesOfThisYear(): Flow<List<DbGame>>

    @Query("SELECT * FROM games WHERE isInMostPopularGamesOfAllTime = 1")
    fun getMostPopularGamesOfAllTime(): Flow<List<DbGame>>

    @Query("SELECT * FROM games WHERE id = :id")
    suspend fun getDetailGameById(id: Int): DbGame?
}
