package com.example.android_gameapplication.fake

import com.example.android_gameapplication.data.GameRepository
import com.example.android_gameapplication.model.Game
import com.example.android_gameapplication.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeApiGamesRepository: GameRepository {
    override suspend fun searchGames(search: String, page: Int): ApiResponse {
        return FakeDataSource.response
    }

    override suspend fun insert(game: Game) {
        FakeDataSource.games.toMutableList().add(game)
    }

    override suspend fun getDetailGameById(id: Int): Flow<Game> {
        return FakeDataSource.games.toMutableList().get(id) as Flow<Game>
    }

    override fun getMostPopularGamesOfThisYear(): Flow<List<Game>> {
        return flowOf(FakeDataSource.games.toMutableList())
    }

    override fun getMostPopularGamesOfAllTime(): Flow<List<Game>> {
        return flowOf(FakeDataSource.games.toMutableList())
    }

    override suspend fun refreshMostPopularGamesOfThisYear() {
        FakeDataSource.games.toMutableList().clear()
    }

    override suspend fun refreshMostPopularGamesOfAllTime() {
        FakeDataSource.games.toMutableList().clear()
    }

    override suspend fun getMostPlayedGamesOfThisYear(page: Int): ApiResponse {
        return FakeDataSource.response
    }

    override suspend fun getMostPlayedGamesOfAllTime(page: Int): ApiResponse {
        return FakeDataSource.response
    }
}
