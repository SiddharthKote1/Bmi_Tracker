package com.example.bmitrackerivinnovations.viewmodel

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CancellationException

class GoogleSignInHelper(
    private val context: Context
) {

    private val auth = FirebaseAuth.getInstance()

    private val credentialManager = CredentialManager.create(context)

    suspend fun signInWithGoogle(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        try {

            // Google Sign-In request
            val googleIdOption = GetGoogleIdOption.Builder()

                // This comes from google-services.json
                .setServerClientId(
                    context.getString(
                        com.example.bmitrackerivinnovations.R.string.default_web_client_id
                    )
                )

                .setFilterByAuthorizedAccounts(false)

                .build()


            // Credential request
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()


            // Show Google account picker
            val result = credentialManager.getCredential(
                context = context,
                request = request
            )


            // Get credential
            val credential = result.credential


            // Check Google credential
            if (
                credential is CustomCredential &&
                credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {

                try {

                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(
                            credential.data
                        )


                    // Get Google ID token
                    val idToken = googleIdTokenCredential.idToken


                    // Firebase credential
                    val firebaseCredential =
                        GoogleAuthProvider.getCredential(
                            idToken,
                            null
                        )


                    // Sign in to Firebase
                    auth.signInWithCredential(firebaseCredential)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {

                                Log.d(
                                    "GoogleSignIn",
                                    "Google Sign-In successful"
                                )

                                onSuccess()

                            } else {

                                Log.e(
                                    "GoogleSignIn",
                                    "Firebase authentication failed",
                                    task.exception
                                )

                                onError(
                                    task.exception?.message
                                        ?: "Firebase authentication failed"
                                )
                            }
                        }

                } catch (e: GoogleIdTokenParsingException) {

                    Log.e(
                        "GoogleSignIn",
                        "Invalid Google ID token",
                        e
                    )

                    onError("Invalid Google credential")

                }

            } else {

                onError("Unexpected credential type")

            }

        } catch (e: CancellationException) {

            throw e

        } catch (e: Exception) {

            Log.e(
                "GoogleSignIn",
                "Google Sign-In failed",
                e
            )

            onError(
                e.message ?: "Google Sign-In failed"
            )
        }
    }
}