package com.example.wallpaperapp.domain.usecases

import android.net.Uri
import com.example.wallpaperapp.domain.repository.DownloadRepository

class DownloadUseCase(
    private val downloadRepository: DownloadRepository
) {
    suspend operator fun invoke(url: Uri,fileName: String) {
        downloadRepository.download(url,fileName)
    }
}