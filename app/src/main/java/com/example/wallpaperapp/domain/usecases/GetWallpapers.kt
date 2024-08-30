package com.example.wallpaperapp.domain.usecases

import com.example.wallpaperapp.data.data_source.remote.wallpaper.Result
import com.example.wallpaperapp.data.response.NetworkResponse
import com.example.wallpaperapp.domain.repository.WallpaperRepository

class GetWallpapers(
    private val repository: WallpaperRepository
) {
    suspend operator fun invoke(): NetworkResponse<List<Result>> {
        repository.getWallpapers().let { wallpaperResponse ->
            return when (wallpaperResponse) {
                is NetworkResponse.Success -> {
                    NetworkResponse.Success(wallpaperResponse.data?.results ?: emptyList())
                }

                is NetworkResponse.Failure -> {
                    NetworkResponse.Failure(wallpaperResponse.error)
                }

                is NetworkResponse.Idle -> {
                    NetworkResponse.Idle()
                }

                is NetworkResponse.Loading -> {
                    NetworkResponse.Loading()
                }
            }
        }
    }
}