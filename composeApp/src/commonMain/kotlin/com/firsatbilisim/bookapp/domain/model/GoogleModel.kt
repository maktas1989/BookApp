package com.firsatbilisim.bookapp.domain.model

data class GoogleModel(
    val id: String,
    val title: String,
    val authors: String,
    val subtitle: String? = null,
    val publisher: String? = null,
    val publishedDate: String? = null,
    val description: String? = null,
    val coverImageUrl: String? = null,
    val pageCount: Int?,
    val language: String? = null, // Kitabın dili (örn: "tr", "en")
    val categories: String? = null, // Kitabın kategorileri (String olarak birleştirilmiş)
    val averageRating: Double? = null, // Ortalama kullanıcı puanı
    val ratingsCount: Int? = null, // Puan veren kullanıcı sayısı
    val previewLink: String? = null, // Kitap önizleme linki (Web/Gömülü)
    val infoLink: String? = null
)
