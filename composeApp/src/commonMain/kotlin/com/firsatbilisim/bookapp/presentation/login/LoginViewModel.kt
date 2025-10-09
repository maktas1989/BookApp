package com.firsatbilisim.bookapp.presentation.login

import androidx.lifecycle.*
import com.firsatbilisim.bookapp.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private var lastRequestTime = 0L

    fun login(email: String, password: String) {
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
            _state.update { it.copy(isLoading = false, errorMessage = "Geçerli e-posta adresi yazınız.") }
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

            val result = loginUseCase(email, password)

            result.fold(
                onSuccess = { user ->
                    _state.update {
                        it.copy(
                            login = user,
                            isLoading = false,
                            isSuccess = true
                        )
                    }
                },
                onFailure = { error ->
                    val message = when {
                        error.message?.contains("supplied auth credential is incorrect") == true ->
                            "E-posta veya şifre hatalı."
                        else -> "Bir hata oluştu: ${error.message ?: "Bilinmeyen hata"}"
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