package com.example.android_gameapplication.ui.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.POST

interface GameApiService {
    @POST("games")
    suspend fun getGames(): List<ApiGame>
}

// Define the interceptor to add the Authorization header
private val authInterceptor = Interceptor { chain ->
    val request: Request = chain.request().newBuilder()
        .addHeader("Client-ID", "")
        .addHeader("Authorization", "")
        .build()
    chain.proceed(request)
}

// Create OkHttpClient with the interceptor
private val okHttpClient = OkHttpClient.Builder().addInterceptor(authInterceptor).build()

private var retrofit: Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl("https://api.igdb.com/v4")
    .build()

//private var retrofit: Retrofit = Retrofit.Builder()
//    .addConverterFactory(
//        Json.asConverterFactory("application/json".toMediaType())
//    )
//    .baseUrl("https://api.igdb.com/v4")
//    .build()

object GameApi {
    val gameService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }
}
