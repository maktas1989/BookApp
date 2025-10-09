package com.firsatbilisim.bookapp.domain.repository

import com.firsatbilisim.bookapp.domain.model.RegisterModel

interface RegisterRepository {
    suspend fun register(email: String, password: String): Result<RegisterModel>
}