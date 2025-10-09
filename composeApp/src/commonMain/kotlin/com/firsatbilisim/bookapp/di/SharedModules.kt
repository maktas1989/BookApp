@file:Suppress("DEPRECATION")

package com.firsatbilisim.bookapp.di

import com.firsatbilisim.bookapp.data.remote.googleService.GoogleService
import com.firsatbilisim.bookapp.data.repository.GoogleRepositoryImpl
import com.firsatbilisim.bookapp.data.repository.RegisterRepositoryImpl
import com.firsatbilisim.bookapp.domain.repository.GoogleRepository
import com.firsatbilisim.bookapp.domain.repository.RegisterRepository
import com.firsatbilisim.bookapp.domain.usecase.GetBookDetailUseCase
import com.firsatbilisim.bookapp.domain.usecase.GetGoogleUseCase
import com.firsatbilisim.bookapp.domain.usecase.RegisterUseCase
import com.firsatbilisim.bookapp.presentation.detail.BookDetailViewModel
import com.firsatbilisim.bookapp.presentation.home.BookHomeViewModel
import com.firsatbilisim.bookapp.presentation.register.RegisterViewModel
import com.firsatbilisim.bookapp.utils.provideDispatcher
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    factory { GoogleService() }
}

val domainModule = module {
    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }

    single<GoogleRepository> { GoogleRepositoryImpl(get()) }

    single<RegisterRepository> { RegisterRepositoryImpl(auth = get(),firestore = get(),ioDispatcher = Dispatchers.IO) }

    factory { GetGoogleUseCase(get()) }
    viewModel { BookHomeViewModel(get()) }

    factory { GetBookDetailUseCase(get()) }
    viewModel { BookDetailViewModel(get()) }

    factory { RegisterUseCase(get()) }
    viewModel { RegisterViewModel(get()) }
}

val utilsModule = module {
    factory { provideDispatcher() }
}

val getSharedModules = listOf(dataModule, domainModule, utilsModule)

fun koinModules() = getSharedModules