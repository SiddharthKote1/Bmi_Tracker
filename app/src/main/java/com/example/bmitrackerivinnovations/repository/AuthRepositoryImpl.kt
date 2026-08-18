package com.example.bmitrackerivinnovations.repository

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.example.bmitrackerivinnovations.R
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(
    private val auth: FirebaseAuth
) : AuthRepository {

    /*
     * Temporary Google credential.
     *
     * We keep this when Firebase tells us that
     * the Google account needs to be linked
     * with an existing Email/Password account.
     */
    private var pendingGoogleCredential: AuthCredential? = null


    // ---------------------------------------------------------
    // EMAIL/PASSWORD SIGN IN
    // ---------------------------------------------------------

    override suspend fun signInWithEmail(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            auth.signInWithEmailAndPassword(
                email,
                password
            ).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }


    // ---------------------------------------------------------
    // EMAIL/PASSWORD SIGN UP
    // ---------------------------------------------------------

    override suspend fun signUpWithEmail(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            auth.createUserWithEmailAndPassword(
                email,
                password
            ).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }


    // ---------------------------------------------------------
    // RESET PASSWORD
    // ---------------------------------------------------------

    override suspend fun resetPassword(
        email: String
    ): Result<Unit> {

        return try {

            auth.sendPasswordResetEmail(
                email
            ).await()

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }


    // ---------------------------------------------------------
    // GOOGLE SIGN IN
    // ---------------------------------------------------------

    override suspend fun signInWithGoogle(
        context: Context
    ): Result<GoogleAuthResult> {

        return try {

            val credentialManager =
                CredentialManager.create(context)


            val googleIdOption =
                GetGoogleIdOption.Builder()

                    .setServerClientId(
                        context.getString(
                            R.string.default_web_client_id
                        )
                    )

                    .setFilterByAuthorizedAccounts(false)

                    .build()


            val request =
                GetCredentialRequest.Builder()

                    .addCredentialOption(
                        googleIdOption
                    )

                    .build()


            // Show Google account picker
            val result =
                credentialManager.getCredential(
                    context = context,
                    request = request
                )


            val credential =
                result.credential


            // Check Google credential
            if (
                credential is CustomCredential &&
                credential.type ==
                GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
            ) {

                val googleCredential =
                    try {

                        GoogleIdTokenCredential
                            .createFrom(
                                credential.data
                            )

                    } catch (
                        e: GoogleIdTokenParsingException
                    ) {

                        return Result.failure(
                            Exception(
                                "Invalid Google credential"
                            )
                        )
                    }


                // Google ID token
                val idToken =
                    googleCredential.idToken


                // Firebase credential
                val firebaseCredential =
                    GoogleAuthProvider.getCredential(
                        idToken,
                        null
                    )


                try {

                    /*
                     * Try normal Google sign in.
                     */
                    auth.signInWithCredential(
                        firebaseCredential
                    ).await()


                    Log.d(
                        "GoogleSignIn",
                        "Google sign-in successful"
                    )

                    Result.success(
                        GoogleAuthResult.Success
                    )

                } catch (
                    e: FirebaseAuthUserCollisionException
                ) {

                    /*
                     * There is already an account
                     * using this email with another provider.
                     *
                     * Save Google credential temporarily.
                     */
                    pendingGoogleCredential =
                        firebaseCredential


                    Result.success(
                        GoogleAuthResult.RequiresPassword
                    )
                }

            } else {

                Result.failure(
                    Exception(
                        "Unexpected credential type"
                    )
                )
            }

        } catch (e: Exception) {

            Log.e(
                "GoogleSignIn",
                "Google sign-in failed",
                e
            )

            Result.failure(e)
        }
    }


    // ---------------------------------------------------------
    // LINK GOOGLE WITH EMAIL/PASSWORD
    // ---------------------------------------------------------

    override suspend fun linkGoogleAccount(
        email: String,
        password: String
    ): Result<Unit> {

        return try {

            val googleCredential =
                pendingGoogleCredential
                    ?: return Result.failure(
                        Exception(
                            "No Google account waiting to be linked"
                        )
                    )


            /*
             * First sign in using the existing
             * Email/Password account.
             */
            val result =
                auth.signInWithEmailAndPassword(
                    email,
                    password
                ).await()


            val currentUser =
                result.user
                    ?: return Result.failure(
                        Exception(
                            "User account not found"
                        )
                    )


            /*
             * Now link Google to the SAME Firebase user.
             */
            currentUser
                .linkWithCredential(
                    googleCredential
                )
                .await()


            /*
             * Linking completed.
             */
            pendingGoogleCredential = null


            Log.d(
                "GoogleSignIn",
                "Google account linked successfully"
            )

            Result.success(Unit)

        } catch (e: Exception) {

            Result.failure(e)
        }
    }
}