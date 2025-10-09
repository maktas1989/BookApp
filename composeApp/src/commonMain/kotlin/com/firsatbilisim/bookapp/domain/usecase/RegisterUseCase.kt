package com.firsatbilisim.bookapp.domain.usecase

import com.firsatbilisim.bookapp.domain.model.RegisterModel
import com.firsatbilisim.bookapp.domain.repository.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<RegisterModel> {
        return repository.register(email, password)
    }
}