package com.firsatbilisim.bookapp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class IosDispatcher: Dispatcher {
    override val io: CoroutineDispatcher get() = Dispatchers.Default
}

actual fun provideDispatcher(): Dispatcher = IosDispatcher()