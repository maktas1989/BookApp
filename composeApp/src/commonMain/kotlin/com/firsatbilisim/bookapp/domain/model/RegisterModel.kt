package com.firsatbilisim.bookapp.domain.model

data class RegisterModel(
    val uid: String = "",
    val email: String = "",
    val password: String = "",
    val createdAt: Long = 0L,
    val isAdmin: Boolean = false,
    val isBanned: Boolean = false,
    val isActive: Boolean = true,
    val isEmailVerified: Boolean = false
)
