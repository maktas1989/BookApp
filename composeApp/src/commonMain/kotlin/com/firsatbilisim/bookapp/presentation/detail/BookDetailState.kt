package com.firsatbilisim.bookapp.presentation.detail

import com.firsatbilisim.bookapp.domain.model.GoogleModel

data class BookDetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val bookDetail: GoogleModel? = null
)
