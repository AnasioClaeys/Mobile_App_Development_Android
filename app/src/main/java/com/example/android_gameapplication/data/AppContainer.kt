package com.example.android_gameapplication.data

import android.content.Context
import androidx.room.Room
import com.example.android_gameapplication.data.database.GameDao
import com.example.android_gameapplication.data.database.GameDatabase
import com.example.android_gameapplication.network.GameApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val gameRepository: GameRepository
}


class DefaultAppContainer(private val applicationContext: Context) : AppContainer {
    private val BASE_URL = "https://api.rawg.io/api/"
    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(json
            .asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()


    private val gameService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }

    private val gameDb: GameDatabase by lazy {
        Room.databaseBuilder(applicationContext, GameDatabase::class.java, "game_database")
            .build()
    }

    private val gameDao: GameDao by lazy {
        gameDb.gameDao()
    }


    override val gameRepository: GameRepository by lazy {
//        ApiGameRepository(gameService)
        ApiGameRepository(gameDao = gameDao, gamesApiService = gameService)
    }

}
