package com.example.android_gameapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android_gameapplication.model.Game

@Entity(tableName="games")
@TypeConverters(ListTypeConverter::class)
data class DbGame (
    @PrimaryKey
    val id: Int,
    val name: String,
    val genres: List<String>,
    val released: String,
    val platforms: List<String>,
    val backgroundImage: String,
    val playtime: Int,
    val isInMostPopularGamesOfThisYear: Boolean = false,
    val isInMostPopularGamesOfAllTime: Boolean = false,
)

fun Game.asDbGame()= DbGame(
    id = id,
    name = name,
    genres = genres,
    released = released,
    platforms = platforms,
    backgroundImage = backgroundImage,
    playtime = playtime,
)

fun DbGame.asDomainGame() = Game(
    id = id,
    name = name,
    genres = genres,
    released = released,
    platforms = platforms,
    backgroundImage = backgroundImage,
    playtime = playtime,
)

fun List<DbGame>.asDomainGames() = map{it.asDomainGame()}
