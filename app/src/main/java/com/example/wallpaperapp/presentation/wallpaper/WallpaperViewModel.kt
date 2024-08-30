package com.example.wallpaperapp.presentation.wallpaper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wallpaperapp.data.data_source.remote.wallpaper.Result
import com.example.wallpaperapp.data.response.NetworkResponse
import com.example.wallpaperapp.domain.model.Photo
import com.example.wallpaperapp.domain.usecases.GetWallpapers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class WallpaperState(
    val wallpaperResponse: NetworkResponse<List<Result>> = NetworkResponse.Idle(),
    val selectedWallpaperUrl: Result? = null,
    val showBottomSheet: Boolean = false,
    val categories: List<Photo> = emptyList()
)


class WallpaperViewModel(
    private val getWallpapers: GetWallpapers,
) : ViewModel() {
    private val _state = MutableStateFlow(WallpaperState())
    val state = _state.asStateFlow()

    init {
        getWallpapers()
    }

    fun getWallpapers() {
        _state.update {
            it.copy(
                wallpaperResponse = NetworkResponse.Loading()
            )
        }
        viewModelScope.launch {
            val wallpaperResponse = getWallpapers.invoke()
            _state.update {
                it.copy(
                    wallpaperResponse = wallpaperResponse
                )
            }
        }
    }
}