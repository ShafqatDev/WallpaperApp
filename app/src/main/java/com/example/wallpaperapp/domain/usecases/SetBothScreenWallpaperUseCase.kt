package com.example.wallpaperapp.domain.usecases

import com.example.wallpaperapp.domain.repository.WallpaperManagerRepository

class SetBothScreenWallpaperUseCase(private val repository: WallpaperManagerRepository) {
    suspend operator fun invoke(url: String) {
        repository.setBothScreenWallpaperFromURL(url)
    }
}