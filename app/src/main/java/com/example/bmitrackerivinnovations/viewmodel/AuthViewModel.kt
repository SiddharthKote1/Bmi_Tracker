package com.example.bmitrackerivinnovations.viewmodel

import com.example.bmitrackerivinnovations.repository.AuthRepository
import com.example.bmitrackerivinnovations.repository.GoogleAuthResult
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    // ---------------------------------------------------------
    // LOADING
    // ---------------------------------------------------------

    private val _isLoading =
        MutableStateFlow(false)

    val isLoading: StateFlow<Boolean> =
        _isLoading.asStateFlow()


    // ---------------------------------------------------------
    // ERROR
    // ---------------------------------------------------------

    private val _errorMessage =
        MutableStateFlow<String?>(null)

    val errorMessage: StateFlow<String?> =
        _errorMessage.asStateFlow()


    // ---------------------------------------------------------
    // LOGIN SUCCESS
    // ---------------------------------------------------------

    private val _loginSuccess =
        MutableStateFlow(false)

    val loginSuccess: StateFlow<Boolean> =
        _loginSuccess.asStateFlow()


    // ---------------------------------------------------------
    // RESET SUCCESS
    // ---------------------------------------------------------

    private val _resetSuccess =
        MutableStateFlow(false)

    val resetSuccess: StateFlow<Boolean> =
        _resetSuccess.asStateFlow()


    // ---------------------------------------------------------
    // GOOGLE NEEDS PASSWORD
    // ---------------------------------------------------------

    private val _googleNeedsPassword =
        MutableStateFlow(false)

    val googleNeedsPassword: StateFlow<Boolean> =
        _googleNeedsPassword.asStateFlow()


    // ---------------------------------------------------------
    // EMAIL SIGN IN
    // ---------------------------------------------------------

    fun signInWithEmail(
        email: String,
        password: String
    ) {

        if (email.isBlank()) {

            _errorMessage.value =
                "Please enter your email"

            return
        }


        if (password.isBlank()) {

            _errorMessage.value =
                "Please enter your password"

            return
        }


        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null
            _loginSuccess.value = false


            val result =
                repository.signInWithEmail(
                    email,
                    password
                )


            _isLoading.value = false


            result
                .onSuccess {

                    _loginSuccess.value = true
                }

                .onFailure { exception ->

                    _errorMessage.value =
                        getFirebaseErrorMessage(
                            exception
                        )
                }
        }
    }


    // ---------------------------------------------------------
    // EMAIL SIGN UP
    // ---------------------------------------------------------

    fun signUpWithEmail(
        email: String,
        password: String
    ) {

        if (email.isBlank()) {

            _errorMessage.value =
                "Please enter your email"

            return
        }


        if (password.length < 6) {

            _errorMessage.value =
                "Password must be at least 6 characters"

            return
        }


        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null


            val result =
                repository.signUpWithEmail(
                    email,
                    password
                )


            _isLoading.value = false


            result
                .onSuccess {

                    _loginSuccess.value = true
                }

                .onFailure { exception ->

                    _errorMessage.value =
                        getFirebaseErrorMessage(
                            exception
                        )
                }
        }
    }


    // ---------------------------------------------------------
    // GOOGLE SIGN IN
    // ---------------------------------------------------------

    fun signInWithGoogle(
        context: Context
    ) {

        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null
            _googleNeedsPassword.value = false


            val result =
                repository.signInWithGoogle(
                    context
                )


            _isLoading.value = false


            result
                .onSuccess { googleResult ->

                    when (googleResult) {

                        GoogleAuthResult.Success -> {

                            _loginSuccess.value = true
                        }

                        GoogleAuthResult.RequiresPassword -> {

                            /*
                             * Existing Email/Password
                             * account found.
                             *
                             * UI should now ask the user
                             * for their existing password.
                             */
                            _googleNeedsPassword.value = true
                        }
                    }
                }

                .onFailure { exception ->

                    _errorMessage.value =
                        getFirebaseErrorMessage(
                            exception
                        )
                }
        }
    }


    // ---------------------------------------------------------
    // LINK GOOGLE ACCOUNT
    // ---------------------------------------------------------

    fun linkGoogleAccount(
        email: String,
        password: String
    ) {

        if (email.isBlank()) {

            _errorMessage.value =
                "Please enter your email"

            return
        }


        if (password.isBlank()) {

            _errorMessage.value =
                "Please enter your existing password"

            return
        }


        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null


            val result =
                repository.linkGoogleAccount(
                    email,
                    password
                )


            _isLoading.value = false


            result
                .onSuccess {

                    _googleNeedsPassword.value = false
                    _loginSuccess.value = true
                }

                .onFailure { exception ->

                    _errorMessage.value =
                        getFirebaseErrorMessage(
                            exception
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

            _errorMessage.value =
                "Please enter your email"

            return
        }


        viewModelScope.launch {

            _isLoading.value = true
            _errorMessage.value = null
            _resetSuccess.value = false


            val result =
                repository.resetPassword(
                    email
                )


            _isLoading.value = false


            result
                .onSuccess {

                    _resetSuccess.value = true
                }

                .onFailure { exception ->

                    _errorMessage.value =
                        getFirebaseErrorMessage(
                            exception
                        )
                }
        }
    }


    // ---------------------------------------------------------
    // FIREBASE ERROR MESSAGE
    // ---------------------------------------------------------

    private fun getFirebaseErrorMessage(
        exception: Throwable
    ): String {

        val message =
            exception.message
                ?: return "Something went wrong"


        return when {

            message.contains(
                "password is invalid",
                ignoreCase = true
            ) ->
                "Incorrect email or password"


            message.contains(
                "no user record",
                ignoreCase = true
            ) ->
                "No account found with this email"


            message.contains(
                "email address is already in use",
                ignoreCase = true
            ) ->
                "An account already exists with this email"


            message.contains(
                "badly formatted",
                ignoreCase = true
            ) ->
                "Invalid email address"


            else ->
                message
        }
    }


    fun clearError() {
        _errorMessage.value = null
    }
}