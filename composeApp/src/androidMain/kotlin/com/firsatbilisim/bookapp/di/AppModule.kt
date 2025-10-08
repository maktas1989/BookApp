@file:Suppress("DEPRECATION")

package com.firsatbilisim.bookapp.di

import com.firsatbilisim.bookapp.presentation.home.BookHomeViewModel
import com.firsatbilisim.bookapp.data.repository.GoogleRepositoryImpl
import com.firsatbilisim.bookapp.domain.repository.GoogleRepository
import com.firsatbilisim.bookapp.domain.usecase.GetBookDetailUseCase
import com.firsatbilisim.bookapp.domain.usecase.GetGoogleUseCase
import com.firsatbilisim.bookapp.presentation.detail.BookDetailViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single<GoogleRepository> { GoogleRepositoryImpl(get()) }

    single { GetGoogleUseCase(get()) }
    viewModel { BookHomeViewModel(get()) }

    single { GetBookDetailUseCase(get()) }
    viewModel { BookDetailViewModel(get()) }
}