package com.firsatbilisim.bookapp.other

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.firsatbilisim.bookapp.presentation.theme.SharedAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            SharedAppTheme {
                RedirectionPage()
            }
        }
    }
}