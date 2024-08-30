package com.example.wallpaperapp.presentation.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.domain.model.Photo
import com.example.wallpaperapp.domain.usecases.GetPhotoFromGalleryUseCase
import com.example.wallpaperapp.domain.usecases.SetBothScreenWallpaperFromBitmapUseCase
import com.example.wallpaperapp.domain.usecases.SetHomeScreenWallpaperFromBitmapUseCase
import com.example.wallpaperapp.domain.usecases.SetLockScreenWallpaperFromBitmapUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CategoryState(
    val photoList: List<Photo> = emptyList(),
    val selectedWallpaperUrl: Photo? = null,
    val showBottomSheet: Boolean = false,
    val isLoading: Boolean = false
)

class GalleryViewModel(
    private val getPhotoFromGalleryUseCase: GetPhotoFromGalleryUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(CategoryState())
    val state = _state.asStateFlow()

    init {
        getAllPhotosFromGallery()
    }

    fun getAllPhotosFromGallery() {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            val photos = getPhotoFromGalleryUseCase.invoke()
            _state.update {
                it.copy(
                    photoList = photos, isLoading = false
                )
            }
        }
    }


}