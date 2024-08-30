package com.example.wallpaperapp.data.data_source.remote.wallpaper

import kotlinx.serialization.Serializable

@Serializable
data class PaymentMethod(
    val price: Int?=null,
    val type: String
)