package com.firsatbilisim.bookapp.presentation.home

import com.firsatbilisim.bookapp.domain.model.GoogleModel

data class BookHomeState(
    val isLoading: Boolean = false,
    val refresh: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val search: String = "",
    val books: List<GoogleModel> = emptyList(),
)