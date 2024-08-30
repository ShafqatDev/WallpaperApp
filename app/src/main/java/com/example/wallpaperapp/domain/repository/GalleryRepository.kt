package com.example.wallpaperapp.domain.repository

import com.example.wallpaperapp.domain.model.Photo


interface GalleryRepository {
    suspend fun getPhotosFromGallery(): List<Photo>
}