package com.firsatbilisim.bookapp.domain.model

data class LoginModel(
    val uid: String,
    val email: String,
    val isActive: Boolean,
    val isBanned: Boolean,
    val isAdmin: Boolean,
    val isEmailVerified: Boolean,
    val createdAt: Long
)
