package com.example.bmitrackerivinnovations.classes

sealed class AuthState {

    data object Idle : AuthState()

    data object Loading : AuthState()

    data object Success : AuthState()

    data class Error(
        val message: String
    ) : AuthState()
}