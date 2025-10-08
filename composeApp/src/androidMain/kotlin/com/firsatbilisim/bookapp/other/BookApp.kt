package com.firsatbilisim.bookapp.other

import android.app.Application
import com.firsatbilisim.bookapp.di.appModule
import com.firsatbilisim.bookapp.di.koinModules
import org.koin.core.context.GlobalContext

class BookApp: Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            modules(appModule + koinModules())
        }
    }
}