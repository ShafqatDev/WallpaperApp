package com.example.wallpaperapp.data.mapper

import com.example.wallpaperapp.data.data_source.remote.wallpaper.ContentSpecific
import com.example.wallpaperapp.data.data_source.remote.wallpaper.Result
import com.example.wallpaperapp.domain.model.Photo

fun Photo.toResult(): Result {
    return Result(
        category = "",
        contentSpecific = ContentSpecific(
            previewUrl = imageUrl.toString(),
            thumbUrl = imageUrl.toString(),
            microThumb = imageUrl.toString()
        ),
        dateUploaded = 0,
        description = "",
        downloadCount = 0,
        id = id,
        licensed = false,
        paymentLock = null,
        paymentMethod = null,
        profile = null,
        recommender = null,
        shareUrl = null,
        stats = null,
        tags = emptyList(),
        title = displayName ?: "",
        type = ""
    )
}
