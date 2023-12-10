package com.example.android_gameapplication.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.android_gameapplication.keys.ApiKeys
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType


import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar


interface GameApiService {
    @GET("games")
    suspend fun getGames(@Query("key") apiKey: String = ApiKeys.API_KEY): ApiResponse

    private fun calculateThisYearDates(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        return "$year-01-01,$year-12-31"
    }

    @GET("games")
    suspend fun getMostPopularGamesOfThisYear(
        @Query("dates") dates: String = calculateThisYearDates(),
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

fun GameApiService.getGamesAsFlow() = flow{
    emit(getMostPopularGamesOfAllTime())
    emit(getMostPopularGamesOfThisYear())
}
