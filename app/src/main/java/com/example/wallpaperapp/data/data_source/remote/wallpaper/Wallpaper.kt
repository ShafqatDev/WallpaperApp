package com.example.wallpaperapp.data.data_source.remote.wallpaper

import kotlinx.serialization.Serializable

@Serializable
data class Wallpaper(
    val pageIndex: Int,
    val results: List<Result>,
    val totalResults: Int
)