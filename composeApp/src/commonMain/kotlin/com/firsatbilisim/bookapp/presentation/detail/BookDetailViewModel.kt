package com.firsatbilisim.bookapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firsatbilisim.bookapp.domain.usecase.GetBookDetailUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val getBookDetailUseCase: GetBookDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(BookDetailState())
    val state: StateFlow<BookDetailState> = _state

    fun loadBookDetail(bookId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, isError = false) }

            try {
                getBookDetailUseCase(bookId).collect { book ->
                    _state.update {
                        it.copy(
                            bookDetail = book,
                            isLoading = false,
                            isError = false
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = "Yükleme Başarısız: ${e.message}"
                    )
                }
                e.printStackTrace()
            }
        }

    }
}
