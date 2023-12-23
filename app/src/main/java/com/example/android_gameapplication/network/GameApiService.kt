package com.example.android_gameapplication.network

import com.example.android_gameapplication.keys.ApiKeys
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Calendar

/**
 * Interface defining the API service for game-related operations.
 * Contains methods to fetch game data from a remote server using HTTP requests.
 */
interface GameApiService {
    /**
     * Searches for games based on a query string.
     *
     * @param search The search query.
     * @param pageSize The number of results per page.
     * @param apiKey The API key for authentication.
     * @param page The page number for pagination.
     * @return [ApiResponse] containing the search results.
     */
    @GET("games")
    suspend fun searchGames(
        @Query("search") search: String,
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
        @Query("page") page: Int = 1,
    ): ApiResponse

    /**
     * Calculates the date range for the current year.
     * This is a helper function for queries that need to filter games within the current year.
     *
     * @return A string representing the date range of the current year.
     */
    private fun calculateThisYearDates(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        return "$year-01-01,$year-12-31"
    }

    /**
     * Retrieves the most popular games of the current year.
     *
     * @param dates The date range for filtering games within the year.
     * @param ordering The criteria for ordering the results.
     * @param pageSize The number of results per page.
     * @param apiKey The API key for authentication.
     * @return [ApiResponse] containing the list of popular games.
     */
    @GET("games")
    suspend fun getMostPopularGamesOfThisYear(
        @Query("dates") dates: String = calculateThisYearDates(),
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
    ): ApiResponse

    /**
     * Retrieves the most popular games of all time.
     *
     * @param ordering The criteria for ordering the results.
     * @param pageSize The number of results per page.
     * @param apiKey The API key for authentication.
     * @return [ApiResponse] containing the list of popular games.
     */
    @GET("games")
    suspend fun getMostPopularGamesOfAllTime(
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
    ): ApiResponse

    /**
     * Fetches details of a specific game by its ID.
     *
     * @param id The unique identifier of the game.
     * @param apiKey The API key for authentication.
     * @return [ApiGame] containing the detailed information of the game.
     */
    @GET("games/{id}")
    suspend fun getGameDetailById(
        @Path("id") id: Int,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
    ): ApiGame

    /**
     * Retrieves the most played games of the current year.
     *
     * @param dates The date range for filtering games within the year.
     * @param ordering The criteria for ordering the results.
     * @param pageSize The number of results per page.
     * @param apiKey The API key for authentication.
     * @param page The page number for pagination.
     * @return [ApiResponse] containing the list of played games.
     */
    @GET("games")
    suspend fun getMostPlayedGamesOfThisYear(
        @Query("dates") dates: String = calculateThisYearDates(),
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
        @Query("page") page: Int = 1,
    ): ApiResponse

    /**
     * Retrieves the most played games of all time.
     *
     * @param ordering The criteria for ordering the results.
     * @param pageSize The number of results per page.
     * @param apiKey The API key for authentication.
     * @param page The page number for pagination.
     * @return [ApiResponse] containing the list of played games.
     */
    @GET("games")
    suspend fun getMostPlayedGamesOfAllTime(
        @Query("ordering") ordering: String = "-added",
        @Query("page_size") pageSize: Int = 10,
        @Query("key") apiKey: String = ApiKeys.API_KEY,
        @Query("page") page: Int = 1,
    ): ApiResponse
}
