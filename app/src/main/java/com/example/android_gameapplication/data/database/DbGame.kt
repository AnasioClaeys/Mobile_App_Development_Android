package com.example.android_gameapplication.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.android_gameapplication.model.Game

/**
 * Data class representing a game entity in the database.
 *
 * @property id The unique identifier of the game.
 * @property name The name of the game.
 * @property genres A list of genres associated with the game.
 * @property released The release date of the game.
 * @property platforms A list of platforms on which the game is available.
 * @property backgroundImage URL for the background image of the game.
 * @property playtime The average playtime of the game.
 * @property isInMostPopularGamesOfThisYear Flag indicating if the game is among the most popular games of the current year.
 * @property isInMostPopularGamesOfAllTime Flag indicating if the game is among the most popular games of all time.
 */
@Entity(tableName = "games")
@TypeConverters(ListTypeConverter::class)
data class DbGame(
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

/**
 * Extension function to convert [Game] to [DbGame].
 *
 * @return The database model equivalent of the game.
 */
fun Game.asDbGame() = DbGame(
    id = id,
    name = name,
    genres = genres,
    released = released,
    platforms = platforms,
    backgroundImage = backgroundImage,
    playtime = playtime,
    isInMostPopularGamesOfThisYear = isPopularGamesOfThisYear,
    isInMostPopularGamesOfAllTime = isPopularGamesOfAllTime,
)

/**
 * Extension function to convert [DbGame] to domain model [Game].
 *
 * @return The domain model equivalent of the database game.
 */
fun DbGame.asDomainGame() = Game(
    id = id,
    name = name,
    genres = genres,
    released = released,
    platforms = platforms,
    backgroundImage = backgroundImage,
    playtime = playtime,
    isPopularGamesOfThisYear = isInMostPopularGamesOfThisYear,
    isPopularGamesOfAllTime = isInMostPopularGamesOfAllTime,
)

/**
 * Extension function to convert a list of [DbGame] to a list of domain model [Game].
 *
 * @return A list of domain model games equivalent to the list of database games.
 */
fun List<DbGame>.asDomainGames() = map { it.asDomainGame() }
