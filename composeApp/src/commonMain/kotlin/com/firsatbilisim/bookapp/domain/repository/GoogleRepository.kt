package com.firsatbilisim.bookapp.domain.repository

import com.firsatbilisim.bookapp.domain.model.GoogleModel
import kotlinx.coroutines.flow.Flow

interface GoogleRepository {
    fun searchBooks(query: String): Flow<List<GoogleModel>>
    fun getBookDetail(bookId: String): Flow<GoogleModel>
}