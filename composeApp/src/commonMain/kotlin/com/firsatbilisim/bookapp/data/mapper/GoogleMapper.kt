package com.firsatbilisim.bookapp.data.mapper

import com.firsatbilisim.bookapp.data.remote.google.GoogleRemote
import com.firsatbilisim.bookapp.domain.model.GoogleModel

fun GoogleRemote.toBook(): GoogleModel {
    val info = this.volumeInfo
    val authorsString = info.authors?.joinToString(separator = ", ") ?: "Bilinmeyen Yazar"
    val cover = info.imageLinks?.thumbnail ?: info.imageLinks?.smallThumbnail
    val year = info.publishedDate?.split("-")?.firstOrNull() ?: "Bilinmiyor"
    val categoriesString = info.categories?.joinToString(separator = ", ") ?: "Bilinmiyor"

    return GoogleModel(
        id = id,
        title = info.title,
        authors = authorsString,
        subtitle = info.subtitle,
        publisher = info.publisher,
        publishedDate = year,
        description = info.description,
        coverImageUrl = cover,

        // 🚨 DETAY, YENİ ALANLAR BURADA EKLENİYOR:
        pageCount = info.pageCount,
        language = info.language,
        categories = categoriesString,
        averageRating = info.averageRating,
        ratingsCount = info.ratingsCount,
        previewLink = info.previewLink,
        infoLink = info.infoLink
    )
}