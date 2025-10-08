package com.firsatbilisim.bookapp.data.remote.google

import kotlinx.serialization.Serializable

@Serializable
data class GoogleRemote(
    val id: String,
    val volumeInfo: VolumeInfoRemote
)

@Serializable
data class VolumeInfoRemote(
    val title: String,
    val subtitle: String? = null,
    val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val imageLinks: ImageLinksRemote? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val language: String? = null,
    val averageRating: Double? = null, // Varsa Double tipinde gelir
    val ratingsCount: Int? = null,     // Varsa Int tipinde gelir
    val previewLink: String? = null,
    val infoLink: String? = null
)

@Serializable
data class ImageLinksRemote(
    val smallThumbnail: String? = null,
    val thumbnail: String? = null
)
