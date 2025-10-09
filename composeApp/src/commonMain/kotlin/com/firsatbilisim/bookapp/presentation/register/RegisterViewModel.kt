package com.firsatbilisim.bookapp.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firsatbilisim.bookapp.domain.usecase.RegisterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
): ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    private var lastRequestTime = 0L

    fun register(email: String, password: String) {
        val now = Clock.System.now().toEpochMilliseconds()
        if (now - lastRequestTime < 1000) {
            _state.update { it.copy(errorMessage = "Lütfen biraz bekleyin...") }
            return
        }
        lastRequestTime = now

        if (email.isBlank() || password.isBlank()) {
            _state.update {
                it.copy(errorMessage = "E-posta ve şifre boş olamaz.")
            }
            return
        }

        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}".toRegex())) {
            _state.update { it.copy(isLoading = false, errorMessage = "Geçersiz e-posta adresi yazınız.") }
            return
        }

        if (password.length < 6) {
            _state.update {
                it.copy(errorMessage = "Şifre en az 6 karakter olmalıdır.")
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null, isSuccess = false) }

            val result = registerUseCase(email, password)

            result.fold(
                onSuccess = { user ->
                    _state.update {
                        it.copy(
                            register = user,
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                },
                onFailure = { error ->
                    val message = when {
                        error.message?.contains("The email address is already in use") == true ->
                            "Bu e-posta adresi zaten kayıtlı."
                        else -> "Kayıt başarısız: ${error.message ?: "Bilinmeyen bir hata oluştu."}"
                    }

                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = message
                        )
                    }
                }
            )
        }
    }





}