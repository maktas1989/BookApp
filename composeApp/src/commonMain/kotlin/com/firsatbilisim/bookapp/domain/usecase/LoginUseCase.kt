package com.firsatbilisim.bookapp.domain.usecase

import com.firsatbilisim.bookapp.domain.model.LoginModel
import com.firsatbilisim.bookapp.domain.repository.LoginRepository

class LoginUseCase(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<LoginModel> {
        return repository.login(email, password)
    }
}