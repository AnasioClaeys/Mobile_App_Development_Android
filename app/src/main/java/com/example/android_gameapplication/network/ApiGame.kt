package com.example.android_gameapplication.network

import com.example.android_gameapplication.model.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class representing the API response structure.
 *
 * @property count Total number of results available.
 * @property next URL for the next page of results, if available.
 * @property previous URL for the previous page of results, if available.
 * @property results A list of games fetched from the API.
 */
@Serializable
data class ApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ApiGame>,
)

/**
 * Data class representing a game structure in API responses.
 *
 * @property id The unique identifier of the game.
 * @property name The name of the game.
 * @property released The release date of the game.
 * @property genres The list of genres associated with the game.
 * @property platforms The list of platforms on which the game is available.
 * @property backgroundImage URL for the background image of the game.
 * @property playtime The average playtime of the game.
 * @property isPopularGamesOfAllTime Flag indicating if the game is among the most popular of all time.
 * @property isPopularGamesOfThisYear Flag indicating if the game is among the most popular of this year.
 */
@Serializable
data class ApiGame(
    val id: Int,
    val name: String,
    val released: String = "No release date",
    @SerialName("genres")
    val genres: List<Genre>? = listOf(Genre("No genre")),
    val platforms: List<Platform>? = listOf(Platform(PlatformDetails("No platform"))),
    @SerialName("background_image")
    val backgroundImage: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/GHS-pictogram-unknown.svg/1200px-GHS-pictogram-unknown.svg.png",
    val playtime: Int,
    val isPopularGamesOfAllTime: Boolean = false,
    val isPopularGamesOfThisYear: Boolean = false,
)

/**
 * Data class for genre details in the API game structure.
 *
 * @property name The name of the genre.
 */
@Serializable
data class Genre(
    val name: String,
)

/**
 * Data class for platform details in the API game structure.
 *
 * @property platform The details of the platform.
 */
@Serializable
data class Platform(
    val platform: PlatformDetails,
)

/**
 * Data class for detailed information about a gaming platform.
 *
 * @property name The name of the platform.
 */
@Serializable
data class PlatformDetails(
    val name: String,
)

/**
 * Extension function to convert [ApiResponse] to a list of domain model [Game] objects.
 *
 * @return A list of [Game] objects derived from the API response.
 */
fun ApiResponse.asDomainObjects(): List<Game> {
    return this.results.map {
        Game(
            id = it.id,
            name = it.name,
            released = it.released,
            genres = it.genres?.map { genre -> genre.name } ?: listOf(),
            platforms = it.platforms?.map { platform -> platform.platform.name } ?: listOf(),
            backgroundImage = it.backgroundImage,
            playtime = it.playtime,
            isPopularGamesOfAllTime = it.isPopularGamesOfAllTime,
            isPopularGamesOfThisYear = it.isPopularGamesOfThisYear,
        )
    }
}

/**
 * Extension function to convert [ApiGame] to domain model [Game].
 *
 * @return A [Game] object derived from the API game data.
 */
fun ApiGame.asDomainObject(): Game {
    return Game(
        id = this.id,
        name = this.name,
        released = this.released,
        genres = this.genres?.map { genre -> genre.name } ?: listOf(),
        platforms = this.platforms?.map { platform -> platform.platform.name } ?: listOf(),
        backgroundImage = this.backgroundImage,
        playtime = this.playtime,
        isPopularGamesOfAllTime = this.isPopularGamesOfAllTime,
        isPopularGamesOfThisYear = this.isPopularGamesOfThisYear,
    )
}
