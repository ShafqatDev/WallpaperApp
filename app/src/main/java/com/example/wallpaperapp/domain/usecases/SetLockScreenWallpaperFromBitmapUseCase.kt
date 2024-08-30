package com.example.wallpaperapp.domain.usecases

import android.content.ContentResolver
import android.net.Uri
import com.example.wallpaperapp.core.utils.Constants.toBitmap
import com.example.wallpaperapp.domain.repository.WallpaperManagerRepository

class SetLockScreenWallpaperFromBitmapUseCase(
    private val repository: WallpaperManagerRepository, private val contentResolver: ContentResolver
) {
    suspend operator fun invoke(url: Uri) {
        repository.setLockScreenWallpaper(url.toBitmap(contentResolver = contentResolver))
    }
}