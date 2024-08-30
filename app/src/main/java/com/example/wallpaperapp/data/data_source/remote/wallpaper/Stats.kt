package com.example.wallpaperapp.data.data_source.remote.wallpaper

import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    val downloads: Int,
    val likes: Int
)