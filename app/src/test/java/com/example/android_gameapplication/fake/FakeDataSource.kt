package com.example.android_gameapplication.fake

import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.ApiGame
import com.example.android_gameapplication.network.ApiResponse
import com.example.android_gameapplication.network.Genre
import com.example.android_gameapplication.network.Platform
import com.example.android_gameapplication.network.PlatformDetails
import kotlinx.serialization.Serializable

object FakeDataSource {

    val games = listOf(
        Game(id = 324997, name = "Baldur's Gate III", genres = listOf("Strategy","Adventure", "RPG"), released = "2023-08-03", platforms = listOf("PC", "PlayStation 5", "macOS"), backgroundImage = "https://media.rawg.io/media/games/699/69907ecf13f172e9e144069769c3be73.jpg", playtime = 9, isPopularGamesOfAllTime = false, isPopularGamesOfThisYear = false),
        Game(id = 324998, name = "Elden Ring", genres = listOf("Action", "RPG"), released = "2023-07-21", platforms = listOf("PC", "Xbox Series X|S"), backgroundImage = "https://media.rawg.io/media/games/123/1234example.jpg", playtime = 15, isPopularGamesOfAllTime = true, isPopularGamesOfThisYear = false),
        Game(id = 324999, name = "Cyberpunk 2077", genres = listOf("Action", "RPG"), released = "2023-04-16", platforms = listOf("PC", "PlayStation 5"), backgroundImage = "https://media.rawg.io/media/games/567/5678example.jpg", playtime = 20, isPopularGamesOfAllTime = false, isPopularGamesOfThisYear = true),
        Game(id = 325000, name = "Horizon Forbidden West", genres = listOf("Action", "Adventure"), released = "2023-02-18", platforms = listOf("PlayStation 5", "PlayStation 4"), backgroundImage = "https://media.rawg.io/media/games/234/2345example.jpg", playtime = 12, isPopularGamesOfAllTime = true, isPopularGamesOfThisYear = true),
        Game(id = 325001, name = "The Witcher 3: Wild Hunt", genres = listOf("Adventure", "RPG"), released = "2023-05-19", platforms = listOf("PC", "PlayStation 4", "Xbox One"), backgroundImage = "https://media.rawg.io/media/games/456/4567example.jpg", playtime = 25, isPopularGamesOfAllTime = true, isPopularGamesOfThisYear = false),
        Game(id = 325002, name = "Hades", genres = listOf("Rogue-like", "Indie"), released = "2023-09-17", platforms = listOf("PC", "Nintendo Switch"), backgroundImage = "https://media.rawg.io/media/games/890/8901example.jpg", playtime = 10, isPopularGamesOfAllTime = false, isPopularGamesOfThisYear = true)
    )


    val games2 = listOf(
        ApiGame(id = 324997, name = "Baldur's Gate III", genres = listOf(Genre("Strategy"), Genre("Adventure"), Genre("RPG")), released = "2023-08-03", platforms = listOf(Platform(PlatformDetails("PC")), Platform(PlatformDetails("PlayStation 5")), Platform(PlatformDetails("macOS"))), backgroundImage = "https://media.rawg.io/media/games/699/69907ecf13f172e9e144069769c3be73.jpg", playtime = 9, isPopularGamesOfAllTime = false, isPopularGamesOfThisYear = false),
        ApiGame(id = 324998, name = "Elden Ring", genres = listOf(Genre("Action"), Genre("RPG")), released = "2023-07-21", platforms = listOf(Platform(PlatformDetails("PC")), Platform(PlatformDetails("Xbox Series X|S"))), backgroundImage = "https://media.rawg.io/media/games/123/1234example.jpg", playtime = 15, isPopularGamesOfAllTime = true, isPopularGamesOfThisYear = false),
        ApiGame(id = 324999, name = "Cyberpunk 2077", genres = listOf(Genre("Action"), Genre("RPG")), released = "2023-04-16", platforms = listOf(Platform(PlatformDetails("PC")), Platform(PlatformDetails("PlayStation 5"))), backgroundImage = "https://media.rawg.io/media/games/567/5678example.jpg", playtime = 20, isPopularGamesOfAllTime = false, isPopularGamesOfThisYear = true),
        ApiGame(id = 325000, name = "Horizon Forbidden West", genres = listOf(Genre("Action"), Genre("Adventure")), released = "2023-02-18", platforms = listOf(Platform(PlatformDetails("PlayStation 5")), Platform(PlatformDetails("PlayStation 4"))), backgroundImage = "https://media.rawg.io/media/games/234/2345example.jpg", playtime = 12, isPopularGamesOfAllTime = true, isPopularGamesOfThisYear = true),
        ApiGame(id = 325001, name = "The Witcher 3: Wild Hunt", genres = listOf(Genre("Adventure"), Genre("RPG")), released = "2023-05-19", platforms = listOf(Platform(PlatformDetails("PC")), Platform(PlatformDetails("PlayStation 4")), Platform(PlatformDetails("Xbox One"))), backgroundImage = "https://media.rawg.io/media/games/456/4567example.jpg", playtime = 25, isPopularGamesOfAllTime = true, isPopularGamesOfThisYear = false),
        ApiGame(id = 325002, name = "Hades", genres = listOf(Genre("Rogue-like"), Genre("Indie")), released = "2023-09-17", platforms = listOf(Platform(PlatformDetails("PC")), Platform(PlatformDetails("Nintendo Switch"))), backgroundImage = "https://media.rawg.io/media/games/890/8901example.jpg", playtime = 10, isPopularGamesOfAllTime = false, isPopularGamesOfThisYear = true)
    )

    val response=
        ApiResponse(
            count =1,
            next = "https://api.rawg.io/api/games?page=2",
            previous = null,
            results = games2
        )

}
