package com.example.wallpaperapp.domain.repository

import android.net.Uri

interface DownloadRepository {
    suspend fun download(url: Uri,fileName: String)
}