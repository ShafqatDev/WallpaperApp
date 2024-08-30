package com.example.wallpaperapp.domain.model

import android.net.Uri

data class Photo(
    val id: String,
    val imageUrl: Uri,
    val displayName: String?
)
