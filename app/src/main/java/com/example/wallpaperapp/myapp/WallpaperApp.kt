package com.example.wallpaperapp.myapp

import android.app.Application
import com.example.wallpaperapp.di.modulesList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WallpaperApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WallpaperApp)
            modules(modulesList)
        }
    }
}