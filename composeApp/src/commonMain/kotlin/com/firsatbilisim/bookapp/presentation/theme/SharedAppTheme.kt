package com.firsatbilisim.bookapp.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val darkStatusBarColor = Color(0xFFFFAB00)
val lightStatusBarColor = Color(0xFFFFC107)


@Composable
fun SharedAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColorScheme(
            primary = darkStatusBarColor,
            tertiary = Color(0xFF3700B3)
        )
    } else {
        lightColorScheme(
            primary = lightStatusBarColor,
            tertiary = Color(0xFF3700B3)
        )
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}
