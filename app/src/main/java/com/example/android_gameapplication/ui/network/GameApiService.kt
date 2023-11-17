package com.example.android_gameapplication.ui.network

import com.example.android_gameapplication.keys.ApiKeys
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApiService {
    @GET("games")
    suspend fun getGames(@Query("key") apiKey: String= ApiKeys.API_KEY): ApiResponse
}

object GameApi {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json{ ignoreUnknownKeys = true }
            .asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://api.rawg.io/api/")
        .build()

    val gameService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }
}
