package com.example.wallpaperapp.domain.usecases

import com.example.wallpaperapp.domain.model.Photo
import com.example.wallpaperapp.domain.repository.GalleryRepository

class GetPhotoFromGalleryUseCase(
    private val galleryRepository: GalleryRepository
) {
    suspend operator fun invoke(): List<Photo> {
        return galleryRepository.getPhotosFromGallery()
    }
}