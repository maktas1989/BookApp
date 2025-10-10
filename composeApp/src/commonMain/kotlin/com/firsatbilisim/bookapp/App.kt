package com.firsatbilisim.bookapp

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.firsatbilisim.bookapp.presentation.detail.BookDetailScreen
import com.firsatbilisim.bookapp.presentation.home.BookHomeScreen
import com.firsatbilisim.bookapp.presentation.login.LoginScreen
import com.firsatbilisim.bookapp.presentation.register.RegisterScreen
import com.firsatbilisim.bookapp.presentation.theme.SharedAppTheme
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth

@Composable
fun App() {
    SharedAppTheme {
        val navController = rememberNavController()

        val isLoggedIn = Firebase.auth.currentUser != null
        val startDestination = if (isLoggedIn) "home" else "login"

        NavHost(navController = navController, startDestination = startDestination) {

            composable("login") {
                LoginScreen(navController = navController)
            }
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
