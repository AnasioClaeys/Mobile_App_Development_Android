package com.example.android_gameapplication.ui.network

import kotlinx.serialization.Serializable

@Serializable
data class ApiGame(
    val year: Int,
    val title: String,
    val genres: List<String>,
    val platforms: List<String>,
    val summary: String,
)
