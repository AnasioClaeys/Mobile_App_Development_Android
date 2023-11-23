package com.example.android_gameapplication.network

import com.example.android_gameapplication.model.Game
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ApiGame>
)

@Serializable
data class ApiGame(
    val id: Int,
    val name: String,
    val released: String,
    @SerialName("genres")
    val genres: List<Genre>?,
    val platforms: List<Platform>?,
    @SerialName("background_image")
    val backgroundImage: String
)

@Serializable
data class Genre(
    val name: String,
)

@Serializable
data class Platform(
    val platform: PlatformDetails,
)

@Serializable
data class PlatformDetails(
    val name: String,
)


fun ApiResponse .asDomainObjects(): List<Game> {
    return this.results.map {
        Game(
            id = it.id,
            name = it.name,
            released = it.released,
            genres = it.genres?.map { genre -> genre.name } ?: listOf(),
            platforms = it.platforms?.map { platform -> platform.platform.name } ?: listOf(),
            backgroundImage = it.backgroundImage
        )
    }

}


fun ApiGame .asDomainObject(): Game {
    return Game(
        id = this.id,
        name = this.name,
        released = this.released,
        genres = this.genres?.map { genre -> genre.name } ?: listOf(),
        platforms = this.platforms?.map { platform -> platform.platform.name } ?: listOf(),
        backgroundImage = this.backgroundImage
    )
}
