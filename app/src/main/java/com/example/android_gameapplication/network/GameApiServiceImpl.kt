package com.example.android_gameapplication.network

import kotlinx.coroutines.flow.flow

class GameApiServiceImpl(private val service: GameApiService) {

    suspend fun getMostPopularGamesOfThisYearProcessed(): ApiResponse {
        val response = service.getMostPopularGamesOfThisYear()
        return response.copy(
            results = response.results.map { it.copy(isPopularGamesOfThisYear = true) },
        )
    }

    suspend fun getMostPopularGamesOfAllTimeProcessed(): ApiResponse {
        val response = service.getMostPopularGamesOfAllTime()
        return response.copy(
            results = response.results.map { it.copy(isPopularGamesOfAllTime = true) },
        )
    }
}

fun GameApiServiceImpl.getMostPopularGamesOfThisYearAsFlow() = flow {
    emit(getMostPopularGamesOfThisYearProcessed())
}

fun GameApiServiceImpl.getMostPopularGamesOfAllTimeAsFlow() = flow {
    emit(getMostPopularGamesOfAllTimeProcessed())
}
