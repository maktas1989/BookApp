package com.firsatbilisim.bookapp.domain.usecase

import com.firsatbilisim.bookapp.domain.model.GoogleModel
import com.firsatbilisim.bookapp.domain.repository.GoogleRepository
import kotlinx.coroutines.flow.Flow

class GetBookDetailUseCase(
    private val googleRepository: GoogleRepository
) {
    operator fun invoke(bookId: String): Flow<GoogleModel> {
        return googleRepository.getBookDetail(bookId)
    }
}
