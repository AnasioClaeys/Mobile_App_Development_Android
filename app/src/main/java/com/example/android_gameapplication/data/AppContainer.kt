package com.example.android_gameapplication.data

import android.content.Context
import androidx.room.Room
import com.example.android_gameapplication.data.database.GameDao
import com.example.android_gameapplication.data.database.GameDatabase
import com.example.android_gameapplication.network.GameApiService
import com.example.android_gameapplication.network.GameApiServiceImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

/**
 * Interface representing a container that holds application-level dependencies.
 */
interface AppContainer {
    val gameRepository: GameRepository
}

/**
 * Default implementation of [AppContainer].
 * Provides dependencies such as API services, database access objects, and repositories.
 *
 * @property applicationContext The context of the application.
 */
class DefaultAppContainer(private val applicationContext: Context) : AppContainer {
    private val BASE_URL = "https://api.rawg.io/api/"
    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            json
                .asConverterFactory("application/json".toMediaType()),
        )
        .baseUrl(BASE_URL)
        .client(createOkHttpClient())
        .build()

    /**
     * Creates an OkHttpClient with logging capabilities.
     *
     * @return Configured OkHttpClient instance.
     */
    private fun createOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BASIC)
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private val gameService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }

    private val gameApiServiceImpl: GameApiServiceImpl by lazy {
        GameApiServiceImpl(gameService)
    }

    private val gameDb: GameDatabase by lazy {
        Room.databaseBuilder(applicationContext, GameDatabase::class.java, "game_database")
            .build()
    }

    private val gameDao: GameDao by lazy {
        gameDb.gameDao()
    }

    /**
     * Provides a repository for accessing game data, using both local database and remote API.
     */
    override val gameRepository: GameRepository by lazy {
        ApiGameRepository(
            gameDao = gameDao,
            gamesApiService = gameService,
            gamesApiServiceImpl = gameApiServiceImpl,
        )
    }
}
