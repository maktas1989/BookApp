package com.firsatbilisim.bookapp.presentation.login

import com.firsatbilisim.bookapp.domain.model.LoginModel

data class LoginState(
    val login: LoginModel? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
