package com.example.android_gameapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DbGame::class], version = 1)
@TypeConverters(ListTypeConverter::class)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
}
