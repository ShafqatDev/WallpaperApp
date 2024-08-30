package com.example.wallpaperapp.data.data_source.remote.wallpaper

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val category: String,
    val contentSpecific: ContentSpecific,
    val dateUploaded: Long? = null,
    val description: String? = null,
    val downloadCount: Int? = null,
    val id: String,
    val licensed: Boolean? = null,
    val origin: Origin? = null,
    val paymentLock: PaymentLock? = null,
    val paymentMethod: PaymentMethod? = null,
    val profile: Profile? = null,
    val recommender: String? = null,
    val shareUrl: String? = null,
    val stats: Stats? = null,
    val tags: List<String>? = null,
    val title: String,
    val type: String? = null
)