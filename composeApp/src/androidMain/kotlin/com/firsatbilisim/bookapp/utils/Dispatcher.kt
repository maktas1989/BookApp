package com.firsatbilisim.bookapp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AndroidDispatcher: Dispatcher {
    override val io: CoroutineDispatcher get() = Dispatchers.IO
}

actual fun provideDispatcher(): Dispatcher = AndroidDispatcher()