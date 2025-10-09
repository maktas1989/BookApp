package com.firsatbilisim.bookapp.data.mapper

import com.firsatbilisim.bookapp.domain.model.LoginModel
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.datetime.Clock

fun FirebaseUser.toLogin(): LoginModel {
    return LoginModel(
        uid = uid,
        email = email.orEmpty(),
        createdAt = Clock.System.now().toEpochMilliseconds(),
        isActive = true,
        isBanned = false,
        isAdmin = false,
        isEmailVerified = false,
    )
}