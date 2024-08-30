package com.example.wallpaperapp.data.data_source.remote.wallpaper

import kotlinx.serialization.Serializable

@Serializable
data class ContentSpecific(
    val microThumb: String,
    val previewUrl: String,
    val thumbUrl: String
)