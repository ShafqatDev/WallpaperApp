package com.example.wallpaperapp.data.repositoryImpl

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.example.wallpaperapp.domain.repository.DownloadRepository
import java.io.File

class DownloadRepositoryImpl(
    private val context: Context
) : DownloadRepository {

    override suspend fun download(url: Uri, fileName: String) {
        val folder = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Videos"
        )
        if (!folder.exists()) {
            folder.mkdirs()
        }
        val newFileName = fileName + System.currentTimeMillis().toString()
        Toast.makeText(context, "Downloading", Toast.LENGTH_SHORT).show()
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val request = DownloadManager.Request(url).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            setTitle(newFileName)
            setDescription("Please Wait")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "$newFileName.png")
            setMimeType("image/png")
        }

        downloadManager.enqueue(request)
    }
}
