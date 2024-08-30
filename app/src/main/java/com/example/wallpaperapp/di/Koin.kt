package com.example.wallpaperapp.di

import android.content.Context
import com.example.wallpaperapp.data.repositoryImpl.DownloadRepositoryImpl
import com.example.wallpaperapp.data.repositoryImpl.GalleryRepositoryImpl
import com.example.wallpaperapp.data.repositoryImpl.WallpaperManagerRepositoryImpl
import com.example.wallpaperapp.data.repositoryImpl.WallpaperRepositoryImpl
import com.example.wallpaperapp.domain.repository.DownloadRepository
import com.example.wallpaperapp.domain.repository.GalleryRepository
import com.example.wallpaperapp.domain.repository.WallpaperManagerRepository
import com.example.wallpaperapp.domain.repository.WallpaperRepository
import com.example.wallpaperapp.domain.usecases.DownloadUseCase
import com.example.wallpaperapp.domain.usecases.GetPhotoFromGalleryUseCase
import com.example.wallpaperapp.domain.usecases.GetWallpapers
import com.example.wallpaperapp.domain.usecases.SetBothScreenWallpaperFromBitmapUseCase
import com.example.wallpaperapp.domain.usecases.SetBothScreenWallpaperUseCase
import com.example.wallpaperapp.domain.usecases.SetHomeScreenWallpaperFromBitmapUseCase
import com.example.wallpaperapp.domain.usecases.SetHomeScreenWallpaperUseCase
import com.example.wallpaperapp.domain.usecases.SetLockScreenWallpaperFromBitmapUseCase
import com.example.wallpaperapp.domain.usecases.SetLockScreenWallpaperUseCase
import com.example.wallpaperapp.presentation.photo_detail.PhotoDetailViewModel
import com.example.wallpaperapp.presentation.gallery.GalleryViewModel
import com.example.wallpaperapp.presentation.wallpaper.WallpaperViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesList = module {
    viewModel { WallpaperViewModel(get()) }
    viewModel {
        GalleryViewModel(get())
    }
    viewModel {
        PhotoDetailViewModel(get(), get(), get(), get(), get(), get(),get())
    }
    factory<GalleryRepository> {
        GalleryRepositoryImpl(get())
    }
    factory<WallpaperManagerRepository> {
        WallpaperManagerRepositoryImpl(get())
    }

    factory<WallpaperRepository> {
        WallpaperRepositoryImpl()
    }
    single {
        SetBothScreenWallpaperUseCase(get())
    }
    single {
        SetLockScreenWallpaperUseCase(get())
    }
    single {
        SetHomeScreenWallpaperUseCase(get())
    }
    single {
        GetWallpapers(get())
    }
    single {
        GetPhotoFromGalleryUseCase(get())
    }
    single {
        SetLockScreenWallpaperFromBitmapUseCase(get(), get())
    }
    single {
        SetBothScreenWallpaperFromBitmapUseCase(get(), get())
    }
    single {
        SetHomeScreenWallpaperFromBitmapUseCase(get(), get())
    }
    single {
        get<Context>().contentResolver
    }

    factory<DownloadRepository> {
        DownloadRepositoryImpl(get())
    }
    single {
        DownloadUseCase(get())
    }
}