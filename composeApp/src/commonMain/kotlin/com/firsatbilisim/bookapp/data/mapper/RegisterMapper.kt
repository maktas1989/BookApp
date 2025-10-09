package com.firsatbilisim.bookapp.data.mapper

import com.firsatbilisim.bookapp.domain.model.RegisterModel
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.datetime.Clock

fun FirebaseUser.toRegister(): RegisterModel {
    return RegisterModel(
        email = email.orEmpty(),
        createdAt = Clock.System.now().toEpochMilliseconds(),
        isActive = true,
        isAdmin = false,
        isBanned = false,
        isEmailVerified = false
    )
}