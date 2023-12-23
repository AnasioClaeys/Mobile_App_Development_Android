package com.example.android_gameapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Abstract Room Database class for the game application.
 * It includes the database configuration and provides DAOs for access to the underlying data.
 *
 * @property entities List of all entities (tables) included in this database.
 * @property version The version number of the database. If you modify the schema, you must increment this value.
 */
@Database(entities = [DbGame::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class GameDatabase : RoomDatabase() {
    /**
     * Abstract method to get the [GameDao] for accessing game-related database operations.
     *
     * @return Instance of [GameDao] for accessing game data.
     */
    abstract fun gameDao(): GameDao
}
