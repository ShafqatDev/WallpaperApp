package com.example.wallpaperapp.data.data_source.remote.wallpaper

import androidx.room.Ignore
import kotlinx.serialization.Serializable

@Serializable
data class PaymentLock(
    @Ignore val price: Int? = null,
    val type: String
)