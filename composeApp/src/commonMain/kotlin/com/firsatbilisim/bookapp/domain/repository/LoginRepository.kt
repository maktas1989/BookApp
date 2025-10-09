package com.firsatbilisim.bookapp.domain.repository

import com.firsatbilisim.bookapp.domain.model.LoginModel

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<LoginModel>
}