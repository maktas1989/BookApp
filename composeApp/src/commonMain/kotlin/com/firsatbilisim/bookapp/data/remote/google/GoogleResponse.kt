package com.firsatbilisim.bookapp.data.remote.google

import kotlinx.serialization.Serializable

@Serializable
data class GoogleResponse(
    val totalItems: Int,
    val items: List<GoogleRemote>? = null
)