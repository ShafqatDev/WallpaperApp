package com.example.wallpaperapp.data.repositoryImpl

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.example.wallpaperapp.core.utils.Constants.getBitmapFromURL
import com.example.wallpaperapp.domain.repository.WallpaperManagerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class WallpaperManagerRepositoryImpl(
    private val context: Context,
) : WallpaperManagerRepository {

    private val wallpaperManager = WallpaperManager.getInstance(context)

    override suspend fun setLockScreenWallpaper(bitmap: Bitmap?) {
        withContext(Dispatchers.IO) {
            try {
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Lock screen wallpaper set successfully",
                        LENGTH_SHORT
                    ).show()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Failed to set lock screen wallpaper",
                        LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override suspend fun setHomeScreenWallpaper(bitmap: Bitmap?) {
        withContext(Dispatchers.IO) {
            try {
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Home screen wallpaper set successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Failed to set home screen wallpaper",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override suspend fun setBothScreenWallpaper(bitmap: Bitmap?) {
        withContext(Dispatchers.IO) {
            try {
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
                wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Wallpaper set on both screens successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Failed to set wallpaper on both screens",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override suspend fun setLockScreenWallpaperFromURL(url: String) {
        withContext(Dispatchers.IO) {
            try {
                val bitmap = getBitmapFromURL(url)
                Log.d("WallpaperRepository", "setLockScreenWallpaperFromURL: Bitmap $bitmap")
                setLockScreenWallpaper(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun setHomeScreenWallpaperFromURL(url: String) {
        withContext(Dispatchers.IO) {
            try {
                val bitmap = getBitmapFromURL(url)
                Log.d("WallpaperRepository", "setHomeScreenWallpaperFromURL: Bitmap $bitmap")
                setHomeScreenWallpaper(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun setBothScreenWallpaperFromURL(url: String) {
        withContext(Dispatchers.IO) {
            try {
                val bitmap = getBitmapFromURL(url)
                Log.d("WallpaperRepository", "setBothScreenWallpaperFromURL: Bitmap $bitmap")
                setBothScreenWallpaper(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}