package com.example.android_gameapplication.network

import com.example.android_gameapplication.keys.ApiKeys
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Calendar

interface GameApiService {
    @GET("games")
    suspend fun searchGames(
        @Query("search") search: String,
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
        @Query("page") page: Int = 1,
    ): ApiResponse

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
        @Query("key") apiKey: String = ApiKeys.API_KEY,
    ): ApiResponse

    @GET("games")
    suspend fun getMostPopularGamesOfAllTime(
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
    ): ApiResponse

    @GET("games/{id}")
    suspend fun getGameDetailById(
        @Path("id") id: Int,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
    ): ApiGame

    @GET("games")
    suspend fun getMostPlayedGamesOfThisYear(
        @Query("dates") dates: String = calculateThisYearDates(),
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
        @Query("page") page: Int = 1,
    ): ApiResponse

    @GET("games")
    suspend fun getMostPlayedGamesOfAllTime(
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
        @Query("page") page: Int = 1,
    ): ApiResponse
}
