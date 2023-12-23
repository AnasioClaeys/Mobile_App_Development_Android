package com.example.android_gameapplication.network

import kotlinx.coroutines.flow.flow

/**
 * Implementation class for game-related API service operations.
 * Provides methods to process and retrieve game data from the API.
 *
 * @property service The instance of [GameApiService] for making API calls.
 */
class GameApiServiceImpl(private val service: GameApiService) {

    /**
     * Retrieves and processes the most popular games of the current year.
     * Marks each game in the response as popular for the current year.
     *
     * @return [ApiResponse] with processed game data.
     */
    suspend fun getMostPopularGamesOfThisYearProcessed(): ApiResponse {
        val response = service.getMostPopularGamesOfThisYear()
        return response.copy(
            results = response.results.map { it.copy(isPopularGamesOfThisYear = true) },
        )
    }

    /**
     * Retrieves and processes the most popular games of all time.
     * Marks each game in the response as popular of all time.
     *
     * @return [ApiResponse] with processed game data.
     */
    suspend fun getMostPopularGamesOfAllTimeProcessed(): ApiResponse {
        val response = service.getMostPopularGamesOfAllTime()
        return response.copy(
            results = response.results.map { it.copy(isPopularGamesOfAllTime = true) },
        )
    }
}

/**
 * Extension function to convert the result of most popular games of the current year to a flow.
 *
 * @return A [flow] emitting the processed [ApiResponse] of most popular games of the current year.
 */
fun GameApiServiceImpl.getMostPopularGamesOfThisYearAsFlow() = flow {
    emit(getMostPopularGamesOfThisYearProcessed())
}

/**
 * Extension function to convert the result of most popular games of all time to a flow.
 *
 * @return A [flow] emitting the processed [ApiResponse] of most popular games of all time.
 */
fun GameApiServiceImpl.getMostPopularGamesOfAllTimeAsFlow() = flow {
    emit(getMostPopularGamesOfAllTimeProcessed())
}
