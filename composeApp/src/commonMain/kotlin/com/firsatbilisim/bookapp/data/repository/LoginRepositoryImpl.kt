package com.firsatbilisim.bookapp.data.repository

import com.firsatbilisim.bookapp.data.mapper.toLogin
import com.firsatbilisim.bookapp.domain.model.LoginModel
import com.firsatbilisim.bookapp.domain.repository.LoginRepository
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class LoginRepositoryImpl(
    private val auth: FirebaseAuth,
    private val ioDispatcher: CoroutineContext
) : LoginRepository {
    override suspend fun login(email: String, password: String): Result<LoginModel> = withContext(ioDispatcher) {
        try {
            val result = auth.signInWithEmailAndPassword(email, password)
            val user = result.user

            if (user == null) {
                return@withContext Result.failure(Exception("Kullanıcı bilgisi alınamadı."))
            }

            val loginModel = user.toLogin()
            Result.success(loginModel)

        } catch (e: Exception) {
            println("Firebase Auth Giriş Hatası: ${e.message}")
            Result.failure(e)
        }
    }
}