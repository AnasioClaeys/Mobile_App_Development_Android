package com.example.android_gameapplication.data

import com.example.android_gameapplication.network.GameApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val gamesRepository: GamesRepository
}


class DefaultAppContainer() : AppContainer {
    private val BASE_URL = "https://api.rawg.io/api/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json { ignoreUnknownKeys = true }
            .asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()


    private val gameService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }


    override val gamesRepository: GamesRepository by lazy {
        ApiGamesRepository(gameService)
    }

}
