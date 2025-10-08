package com.firsatbilisim.bookapp

import androidx.compose.ui.window.ComposeUIViewController
import com.firsatbilisim.bookapp.di.initKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    initKoin() // Koin burada başlatılır
    return ComposeUIViewController {
        App()
    }
}
