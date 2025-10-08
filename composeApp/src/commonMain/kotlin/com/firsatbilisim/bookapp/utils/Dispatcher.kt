package com.firsatbilisim.bookapp.utils

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    val io: CoroutineDispatcher
}

expect fun provideDispatcher(): Dispatcher