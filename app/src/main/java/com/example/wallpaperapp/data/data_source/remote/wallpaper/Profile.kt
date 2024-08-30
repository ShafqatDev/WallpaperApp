package com.example.wallpaperapp.data.data_source.remote.wallpaper

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val avatarIconUrl: String,
    val id: String,
    val name: String,
    val shareUrl: String,
    val verified: Boolean
)