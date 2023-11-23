package com.example.android_gameapplication.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.android_gameapplication.keys.ApiKeys
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate
import java.time.format.DateTimeFormatter




interface GameApiService {
    @GET("games")
    suspend fun getGames(@Query("key") apiKey: String = ApiKeys.API_KEY): ApiResponse

    @GET("games")
    suspend fun getMostPopularGamesOfThisYear(
        @Query("dates") dates: String = "2023-01-01,2023-12-31",
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY
    ): ApiResponse

    @GET("games")
    suspend fun getMostPopularGamesOfAllTime(
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY
    ): ApiResponse

    @GET("games/{id}")
    suspend fun getGameDetailById(
        @Path("id") id:Int,
        @Query("key") apiKey: String = ApiKeys.API_KEY
    ): ApiGame
}
