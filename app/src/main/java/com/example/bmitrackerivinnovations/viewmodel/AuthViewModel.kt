package com.example.bmitrackerivinnovations.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmitrackerivinnovations.classes.AuthState
import com.example.bmitrackerivinnovations.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repository = AuthRepository()

    private val _authState =
        MutableStateFlow<AuthState>(AuthState.Idle)

    val authState: StateFlow<AuthState> =
        _authState.asStateFlow()


    // ---------------------------------------------------------
    // REGISTER
    // ---------------------------------------------------------

    fun register(
        email: String,
        password: String
    ) {

        if (email.isBlank()) {

            _authState.value =
                AuthState.Error("Email is required")

            return
        }

        if (password.isBlank()) {

            _authState.value =
                AuthState.Error("Password is required")

            return
        }

        if (password.length < 6) {

            _authState.value =
                AuthState.Error(
                    "Password must be at least 6 characters"
                )

            return
        }

        viewModelScope.launch {

            _authState.value =
                AuthState.Loading

            val result =
                repository.registerUser(
                    email = email.trim(),
                    password = password
                )

            _authState.value =
                if (result.isSuccess) {

                    AuthState.Success

                } else {

                    AuthState.Error(
                        getFirebaseError(
                            result.exceptionOrNull()
                        )
                    )
                }
        }
    }


    // ---------------------------------------------------------
    // LOGIN
    // ---------------------------------------------------------

    fun login(
        email: String,
        password: String
    ) {

        if (email.isBlank()) {

            _authState.value =
                AuthState.Error("Email is required")

            return
        }

        if (password.isBlank()) {

            _authState.value =
                AuthState.Error("Password is required")

            return
        }

        viewModelScope.launch {

            _authState.value =
                AuthState.Loading

            val result =
                repository.loginUser(
                    email = email.trim(),
                    password = password
                )

            _authState.value =
                if (result.isSuccess) {

                    AuthState.Success

                } else {

                    AuthState.Error(
                        getFirebaseError(
                            result.exceptionOrNull()
                        )
                    )
                }
        }
    }


    // ---------------------------------------------------------
    // RESET PASSWORD
    // ---------------------------------------------------------

    fun resetPassword(
        email: String
    ) {

        if (email.isBlank()) {

            _authState.value =
                AuthState.Error(
                    "Enter your email address"
                )

            return
        }

        viewModelScope.launch {

            _authState.value =
                AuthState.Loading

            val result =
                repository.resetPassword(
                    email.trim()
                )

            _authState.value =
                if (result.isSuccess) {

                    AuthState.Success

                } else {

                    AuthState.Error(
                        getFirebaseError(
                            result.exceptionOrNull()
                        )
                    )
                }
        }
    }


    // ---------------------------------------------------------
    // CLEAR STATE
    // ---------------------------------------------------------

    fun clearState() {

        _authState.value =
            AuthState.Idle
    }


    // ---------------------------------------------------------
    // FIREBASE ERROR
    // ---------------------------------------------------------

    private fun getFirebaseError(
        exception: Throwable?
    ): String {

        val message =
            exception?.message

        return when {

            message.isNullOrBlank() ->
                "Something went wrong. Please try again."

            message.contains(
                "password is invalid",
                ignoreCase = true
            ) ->
                "Incorrect email or password."

            message.contains(
                "no user record",
                ignoreCase = true
            ) ->
                "No account found with this email."

            message.contains(
                "badly formatted",
                ignoreCase = true
            ) ->
                "Please enter a valid email address."

            message.contains(
                "email address is already in use",
                ignoreCase = true
            ) ->
                "An account already exists with this email."

            message.contains(
                "network",
                ignoreCase = true
            ) ->
                "Network error. Check your internet connection."

            else ->
                message
        }
    }
}