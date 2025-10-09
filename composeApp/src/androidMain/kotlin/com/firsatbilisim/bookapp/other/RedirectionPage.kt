package com.firsatbilisim.bookapp.other

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.firsatbilisim.bookapp.domain.model.GoogleModel // Dummy book için
import com.firsatbilisim.bookapp.presentation.detail.BookDetailScreen
import com.firsatbilisim.bookapp.presentation.home.BookHomeScreen
import com.firsatbilisim.bookapp.presentation.register.RegisterScreen

@Composable
fun RedirectionPage() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "register") {

        composable("register") {
            RegisterScreen(navController = navController)
        }
        composable("home") {
            BookHomeScreen(navController = navController)
        }
        composable(
            route = "detail/{bookId}",
            arguments = listOf(navArgument("bookId") { type = NavType.StringType })
            ) { backStackEntry ->
            val bookId: String? = backStackEntry.arguments?.getString("bookId")
            if (bookId != null) {
                BookDetailScreen(navController = navController, bookId = bookId)
            } else {
                navController.popBackStack()
            }
        }
    }
}
