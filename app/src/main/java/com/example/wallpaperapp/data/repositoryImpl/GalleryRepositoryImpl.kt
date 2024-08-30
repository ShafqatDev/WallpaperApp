package com.example.wallpaperapp.data.repositoryImpl

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.example.wallpaperapp.domain.model.Photo
import com.example.wallpaperapp.domain.repository.GalleryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GalleryRepositoryImpl(
    private val context: Context
) : GalleryRepository {
    override suspend fun getPhotosFromGallery(): List<Photo> {
        return withContext(Dispatchers.IO) {
            val photos = mutableListOf<Photo>()
            val projection = arrayOf(
                MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME
            )
            val sortOrder = "${MediaStore.Images.Media.DATE_ADDED} DESC"
            context.contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection,
                null,
                null,
                sortOrder,
                null
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val displayName = cursor.getString(nameColumn)

                    val imageUrl = Uri.withAppendedPath(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString()
                    )

                    photos.add(
                        Photo(
                            id = id.toString(), imageUrl = imageUrl, displayName = displayName
                        )
                    )
                }
            }

            photos
        }
    }
}