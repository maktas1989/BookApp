package com.firsatbilisim.bookapp.data.repository

import com.firsatbilisim.bookapp.data.mapper.toRegister
import com.firsatbilisim.bookapp.domain.model.RegisterModel
import com.firsatbilisim.bookapp.domain.repository.RegisterRepository
import dev.gitlive.firebase.auth.*
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.withContext
import kotlinx.datetime.Clock
import kotlin.coroutines.CoroutineContext

class RegisterRepositoryImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val ioDispatcher: CoroutineContext
) : RegisterRepository {
    override suspend fun register(email: String, password: String): Result<RegisterModel> = withContext(ioDispatcher) {
        try {
            val result = auth.createUserWithEmailAndPassword(email, password)
            val registeredUser = result.user

            if (registeredUser == null) {
                return@withContext Result.failure(Exception("Kayıt tamamlanamadı: Kullanıcı bilgisi alınamadı."))
            }

            val now = Clock.System.now().toEpochMilliseconds()
            val userData = mapOf(
                "uid" to registeredUser.uid,
                "email" to registeredUser.email,
                "createdAt" to now,
                "isAdmin" to false,
                "isBanned" to false,
                "isActive" to true,
                "isEmailVerified" to false
            )

            firestore.collection("users").document(registeredUser.uid).set(userData)
            Result.success(registeredUser.toRegister())

        } catch (e: Exception) {
            println("Firebase Auth Kayıt Hatası: ${e.message}")
            Result.failure(Exception(e))
        }
    }
}