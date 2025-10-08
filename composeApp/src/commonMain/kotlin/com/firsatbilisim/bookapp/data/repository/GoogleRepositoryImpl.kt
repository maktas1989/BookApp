package com.firsatbilisim.bookapp.data.repository

import com.firsatbilisim.bookapp.data.mapper.toBook
import com.firsatbilisim.bookapp.data.remote.googleService.GoogleService
import com.firsatbilisim.bookapp.domain.model.GoogleModel
import com.firsatbilisim.bookapp.domain.repository.GoogleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GoogleRepositoryImpl(
    private val googleService: GoogleService
): GoogleRepository {
    override fun searchBooks(query: String): Flow<List<GoogleModel>> = flow {
        val response = googleService.searchBooks(query)
        emit(response.items?.map { it.toBook() } ?: emptyList())
    }

    override fun getBookDetail(bookId: String): Flow<GoogleModel> = flow {
        val remote = googleService.getBookDetail(bookId)
        emit(remote.toBook())
    }
}