package com.firsatbilisim.bookapp.presentation.register

import com.firsatbilisim.bookapp.domain.model.RegisterModel

data class RegisterState(
    val register: RegisterModel? = null,
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
