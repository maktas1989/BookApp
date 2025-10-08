package com.firsatbilisim.bookapp.domain.usecase

import com.firsatbilisim.bookapp.domain.model.GoogleModel
import com.firsatbilisim.bookapp.domain.repository.GoogleRepository
import kotlinx.coroutines.flow.Flow

class GetGoogleUseCase(
    private val googleRepository: GoogleRepository
) {
    operator fun invoke(query: String): Flow<List<GoogleModel>> {
        return googleRepository.searchBooks(query)
    }
}