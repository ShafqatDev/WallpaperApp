package com.example.wallpaperapp.core.utils

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

object Constants {
    suspend fun getBitmapFromURL(src: String?): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val url = URL(src)
                val connection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }
    }

    fun Uri.toBitmap(contentResolver: ContentResolver): Bitmap? {
        return try {
            val inputStream = contentResolver.openInputStream(this)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


    const val WALLPAPER_API =
        "https://api.prod.zedge.net/content-browse-v2/v1/ANDROID/modules/31b17cf3-c516-4dbc-a62b-93e2822b2b33/browse?thumbWidth=540&thumbHeight=1200&previewWidth=1080&previewHeight=2400&page=0&size=500"


    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    val permissionList =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            listOf(
                android.Manifest.permission.READ_MEDIA_IMAGES,
                android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
                android.Manifest.permission.INTERNET
            )
        } else {
            listOf(
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.INTERNET
            )
        }
}