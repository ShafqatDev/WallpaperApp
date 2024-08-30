package com.example.wallpaperapp.presentation.photo_detail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.data.data_source.remote.wallpaper.Result
import com.example.wallpaperapp.domain.usecases.DownloadUseCase
import com.example.wallpaperapp.domain.usecases.SetBothScreenWallpaperFromBitmapUseCase
import com.example.wallpaperapp.domain.usecases.SetBothScreenWallpaperUseCase
import com.example.wallpaperapp.domain.usecases.SetHomeScreenWallpaperFromBitmapUseCase
import com.example.wallpaperapp.domain.usecases.SetHomeScreenWallpaperUseCase
import com.example.wallpaperapp.domain.usecases.SetLockScreenWallpaperFromBitmapUseCase
import com.example.wallpaperapp.domain.usecases.SetLockScreenWallpaperUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PhotoDetailState(
    val showBottomSheet: Boolean = false,
    val selectedWallpaperUrlResult: Result? = null,
    val isLoading: Boolean = false,
    val showHideDownloadDialog: Boolean = false
)

class PhotoDetailViewModel(
    private val setBothScreenWallpaperFromBitmapUseCase: SetBothScreenWallpaperFromBitmapUseCase,
    private val setHomeScreenWallpaperFromBitmapUseCase: SetHomeScreenWallpaperFromBitmapUseCase,
    private val setLockScreenWallpaperFromBitmapUseCase: SetLockScreenWallpaperFromBitmapUseCase,
    private val setLockScreenWallpaperUseCase: SetLockScreenWallpaperUseCase,
    private val setHomeScreenWallpaperUseCase: SetHomeScreenWallpaperUseCase,
    private val setBothScreenWallpaperUseCase: SetBothScreenWallpaperUseCase,
    private val downloadUseCase: DownloadUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(PhotoDetailState())
    val state = _state.asStateFlow()

    fun showBottomSheet(result: Result) {
        _state.update {
            it.copy(
                selectedWallpaperUrlResult = result, showBottomSheet = true
            )
        }
    }

    fun hideBottomSheet() {
        _state.update {
            it.copy(
                showBottomSheet = false
            )
        }
    }

    fun setBothScreenWallpaper(wallpaper: Uri, isFromGallery: Boolean = false) {
        try {
            viewModelScope.launch {
                _state.update {
                    it.copy(
                        isLoading = true
                    )
                }
                if (isFromGallery) {
                    setBothScreenWallpaperFromBitmapUseCase.invoke(wallpaper)
                } else {
                    setBothScreenWallpaperUseCase.invoke(wallpaper.toString())
                }
                _state.update {
                    it.copy(
                        isLoading = false
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    fun setHomeScreenWallpaper(wallpaper: Uri, isFromGallery: Boolean = false) {
        try {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            viewModelScope.launch {
                if (isFromGallery) {
                    setHomeScreenWallpaperFromBitmapUseCase.invoke(wallpaper)
                } else {
                    setHomeScreenWallpaperUseCase.invoke(wallpaper.toString())
                }
            }
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    fun setLockScreenWallpaper(wallpaper: Uri, isFromGallery: Boolean = false) {
        try {
            viewModelScope.launch {
                if (isFromGallery) {
                    setLockScreenWallpaperFromBitmapUseCase.invoke(wallpaper)
                } else {
                    setLockScreenWallpaperUseCase.invoke(wallpaper.toString())
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _state.update {
                it.copy(
                    isLoading = false
                )
            }
        }
    }

    fun downloadWallpaper(wallpaper: Uri, fileName: String) {
        viewModelScope.launch {
            downloadUseCase.invoke(wallpaper, fileName)
        }
    }

    fun showHideDownloadDialog() {
        _state.update {
            it.copy(
                showHideDownloadDialog = !it.showHideDownloadDialog
            )
        }
    }
}