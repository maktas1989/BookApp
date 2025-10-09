@file:Suppress("DEPRECATION")

package com.firsatbilisim.bookapp.di

import com.firsatbilisim.bookapp.presentation.home.BookHomeViewModel
import com.firsatbilisim.bookapp.data.repository.GoogleRepositoryImpl
import com.firsatbilisim.bookapp.data.repository.RegisterRepositoryImpl
import com.firsatbilisim.bookapp.domain.repository.GoogleRepository
import com.firsatbilisim.bookapp.domain.repository.RegisterRepository
import com.firsatbilisim.bookapp.domain.usecase.GetBookDetailUseCase
import com.firsatbilisim.bookapp.domain.usecase.GetGoogleUseCase
import com.firsatbilisim.bookapp.domain.usecase.RegisterUseCase
import com.firsatbilisim.bookapp.presentation.detail.BookDetailViewModel
import com.firsatbilisim.bookapp.presentation.register.RegisterViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }

    single<GoogleRepository> { GoogleRepositoryImpl(get()) }

    single<RegisterRepository> { RegisterRepositoryImpl(auth = get(),firestore = get(),ioDispatcher = Dispatchers.IO) }

    single { GetGoogleUseCase(get()) }
    viewModel { BookHomeViewModel(get()) }

    single { GetBookDetailUseCase(get()) }
    viewModel { BookDetailViewModel(get()) }

    single { RegisterUseCase(get()) }
    viewModel { RegisterViewModel(get()) }
}