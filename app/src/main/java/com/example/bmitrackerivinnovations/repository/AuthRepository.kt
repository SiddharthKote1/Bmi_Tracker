package com.example.bmitrackerivinnovations.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun registerUser(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            auth.createUserWithEmailAndPassword(
                email.trim(),
                password
            ).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun loginUser(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            auth.signInWithEmailAndPassword(
                email.trim(),
                password
            ).await()

            Result.success(Unit)

        } catch (e: FirebaseAuthInvalidUserException) {

            Result.failure(
                Exception("No account found with this email address.")
            )

        } catch (e: FirebaseAuthInvalidCredentialsException) {

            Result.failure(
                Exception("Incorrect email or password.")
            )

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    suspend fun resetPassword(
        email: String
    ): Result<Unit> {

        return try {

            auth.sendPasswordResetEmail(
                email.trim()
            ).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }

    fun getCurrentUser() = auth.currentUser

    fun logout() {
        auth.signOut()
    }
}