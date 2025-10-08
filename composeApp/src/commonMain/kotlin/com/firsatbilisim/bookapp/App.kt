package com.firsatbilisim.bookapp

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.firsatbilisim.bookapp.presentation.detail.BookDetailScreen
import com.firsatbilisim.bookapp.presentation.home.BookHomeScreen
import com.firsatbilisim.bookapp.presentation.theme.SharedAppTheme

@Composable
fun App() {
    SharedAppTheme {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                BookHomeScreen(navController = navController)
            }
            composable(
                route = "detail/{bookId}",
                arguments = listOf(navArgument("bookId") { type = NavType.StringType })
            ) { backStackEntry ->
                val bookId = backStackEntry.savedStateHandle.get<String>("bookId")

                if (bookId != null) {
                    BookDetailScreen(
                        navController = navController,
                        bookId = bookId
                    )
                } else {
                    navController.popBackStack()
                }
            }
        }
    }
}
