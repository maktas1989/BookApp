package com.firsatbilisim.bookapp.other

import android.app.Application
import com.firsatbilisim.bookapp.di.appModule
import com.firsatbilisim.bookapp.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BookApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BookApp)
            modules(appModule + koinModules())
        }

    }
}
