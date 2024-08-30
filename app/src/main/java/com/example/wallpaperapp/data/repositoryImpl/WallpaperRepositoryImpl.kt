package com.example.wallpaperapp.data.repositoryImpl

import com.example.wallpaperapp.core.utils.Constants.WALLPAPER_API
import com.example.wallpaperapp.data.controller.NetworkClient
import com.example.wallpaperapp.data.data_source.remote.wallpaper.Wallpaper
import com.example.wallpaperapp.data.response.NetworkResponse
import com.example.wallpaperapp.data.response.RequestTypes
import com.example.wallpaperapp.domain.repository.WallpaperRepository

class WallpaperRepositoryImpl(
) : WallpaperRepository {
    override suspend fun getWallpapers(): NetworkResponse<Wallpaper> {
        return NetworkClient.makeNetworkRequest<Wallpaper>(
            WALLPAPER_API,
            RequestTypes.Get,
        )
    }
}