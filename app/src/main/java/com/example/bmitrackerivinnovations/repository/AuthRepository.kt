package com.example.bmitrackerivinnovations.repository

import android.content.Context

interface AuthRepository {

    suspend fun signInWithEmail(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun signUpWithEmail(
        email: String,
        password: String
    ): Result<Unit>

    suspend fun resetPassword(
        email: String
    ): Result<Unit>

    suspend fun signInWithGoogle(
        context: Context
    ): Result<GoogleAuthResult>

    suspend fun linkGoogleAccount(
        email: String,
        password: String
    ): Result<Unit>
}

sealed class GoogleAuthResult {

    data object Success : GoogleAuthResult()

    data object RequiresPassword : GoogleAuthResult()
}