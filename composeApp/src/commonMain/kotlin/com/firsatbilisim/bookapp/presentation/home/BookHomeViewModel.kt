package com.firsatbilisim.bookapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firsatbilisim.bookapp.domain.usecase.GetGoogleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookHomeViewModel(
    private val getGoogleUseCase: GetGoogleUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(BookHomeState())
    val state: StateFlow<BookHomeState> = _state.asStateFlow()

    fun loadBooksBySearch(query: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, refresh = true, isError = false) }

            try {
                getGoogleUseCase(query).collect { books ->
                    _state.update {
                        it.copy(
                            books = books,
                            isLoading = false,
                            refresh = false,
                        )
                    }
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        isLoading = false,
                        refresh = false,
                        isError = true,
                        errorMessage = "Yükleme Başarısız: ${e.message}"
                    )
                }
                e.printStackTrace()
            }
        }
    }

    fun onRefresh() {
        _state.update { it.copy(refresh = true) }
        loadBooksBySearch(_state.value.search)
    }

    fun onSearchQueryChange(newQuery: String) {
        _state.update { it.copy(search = newQuery) }
    }
}
