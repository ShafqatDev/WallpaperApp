package com.example.wallpaperapp.domain.repository

import android.graphics.Bitmap


interface WallpaperManagerRepository {
    suspend fun setLockScreenWallpaperFromURL(url: String)
    suspend fun setHomeScreenWallpaperFromURL(url: String)
    suspend fun setBothScreenWallpaperFromURL(url: String)
    suspend fun setLockScreenWallpaper(bitmap: Bitmap?)
    suspend fun setHomeScreenWallpaper(bitmap: Bitmap?)
    suspend fun setBothScreenWallpaper(bitmap: Bitmap?)
}
