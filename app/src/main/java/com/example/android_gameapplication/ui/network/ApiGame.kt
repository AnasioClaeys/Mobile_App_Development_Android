package com.example.android_gameapplication.ui.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<ApiGame>
)

@Serializable
data class ApiGame(
    val id: Int,
    val name: String,
    val released: String,
    @SerialName("genres")
    val genres: List<Genre>?,
    val platforms: List<Platform>?,
    @SerialName("background_image")
    val backgroundImage: String
)

@Serializable
data class Genre(
    val name: String,
)

@Serializable
data class Platform(
    val platform: PlatformDetails,
)

@Serializable
data class PlatformDetails(
    val name: String,
)
