package com.example.wallpaperapp.domain.repository

import com.example.wallpaperapp.data.data_source.remote.wallpaper.Wallpaper
import com.example.wallpaperapp.data.response.NetworkResponse

interface WallpaperRepository {
    suspend fun getWallpapers(): NetworkResponse<Wallpaper>
}
