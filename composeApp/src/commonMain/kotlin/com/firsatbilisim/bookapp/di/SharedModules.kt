@file:Suppress("DEPRECATION")

package com.firsatbilisim.bookapp.di

import com.firsatbilisim.bookapp.data.remote.googleService.GoogleService
import com.firsatbilisim.bookapp.data.repository.GoogleRepositoryImpl
import com.firsatbilisim.bookapp.domain.repository.GoogleRepository
import com.firsatbilisim.bookapp.domain.usecase.GetBookDetailUseCase
import com.firsatbilisim.bookapp.domain.usecase.GetGoogleUseCase
import com.firsatbilisim.bookapp.presentation.detail.BookDetailViewModel
import com.firsatbilisim.bookapp.presentation.home.BookHomeViewModel
import com.firsatbilisim.bookapp.utils.provideDispatcher
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    factory { GoogleService() }
}

val domainModule = module {
    single<GoogleRepository> { GoogleRepositoryImpl(get()) }

    factory { GetGoogleUseCase(get()) }
    viewModel { BookHomeViewModel(get()) }

    factory { GetBookDetailUseCase(get()) }
    viewModel { BookDetailViewModel(get()) }
}

val utilsModule = module {
    factory { provideDispatcher() }
}

val getSharedModules = listOf(dataModule, domainModule, utilsModule)

fun koinModules() = getSharedModules