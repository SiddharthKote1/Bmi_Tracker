package com.example.bmitrackerivinnovations.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bmitrackerivinnovations.classes.AuthState
import com.example.bmitrackerivinnovations.navigation.Routes
import com.example.bmitrackerivinnovations.viewmodel.AuthViewModel


@Composable
fun SignupScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }

    var termsAccepted by remember {
        mutableStateOf(false)
    }

    // Local validation messages
    var emailError by remember {
        mutableStateOf("")
    }

    var passwordError by remember {
        mutableStateOf("")
    }

    var confirmPasswordError by remember {
        mutableStateOf("")
    }

    var termsError by remember {
        mutableStateOf("")
    }

    var generalError by remember {
        mutableStateOf("")
    }

    val authState by viewModel.authState.collectAsState()

    /*
     * Observe authentication state from ViewModel
     */
    LaunchedEffect(authState) {

        when (val state = authState) {

            is AuthState.Success -> {

                generalError = ""

                // Registration successful.
                // Go back to Login screen.
                navController.popBackStack()

                viewModel.clearState()
            }

            is AuthState.Error -> {

                generalError = state.message

                viewModel.clearState()
            }

            else -> Unit
        }
    }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF7FBFF)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Back button
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.Start
                ) {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(28.dp)
                )


                Text(
                    text = "Create Account",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )


                Spacer(
                    modifier = Modifier.height(6.dp)
                )


                Text(
                    text = "Fill the details to get started",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF52688A)
                )


                Spacer(
                    modifier = Modifier.height(28.dp)
                )


                // ---------------- EMAIL ----------------

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = ""
                        generalError = ""
                    },
                    modifier = Modifier.fillMaxWidth(),

                    placeholder = {
                        Text(
                            text = "Email address",
                            fontSize = 12.sp,
                            color = Color(0xFF52688A)
                        )
                    },

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email",
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFF52688A)
                        )
                    },

                    singleLine = true,

                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 13.sp
                    ),

                    isError = emailError.isNotEmpty(),

                    shape = RoundedCornerShape(10.dp),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,

                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,

                        focusedBorderColor = Color(0xFFE1E9F2),
                        unfocusedBorderColor = Color(0xFFE1E9F2)
                    )
                )


                if (emailError.isNotEmpty()) {

                    Text(
                        text = emailError,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 4.dp,
                                top = 4.dp
                            ),
                        fontSize = 11.sp,
                        color = Color.Red
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                // ---------------- PASSWORD ----------------

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = ""
                        generalError = ""
                    },

                    modifier = Modifier.fillMaxWidth(),

                    placeholder = {
                        Text(
                            text = "Password",
                            fontSize = 12.sp,
                            color = Color(0xFF52688A)
                        )
                    },

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password",
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFF52688A)
                        )
                    },

                    trailingIcon = {

                        IconButton(
                            onClick = {
                                passwordVisible = !passwordVisible
                            },
                            modifier = Modifier.size(40.dp)
                        ) {

                            Icon(
                                imageVector =
                                    if (passwordVisible) {
                                        Icons.Default.VisibilityOff
                                    } else {
                                        Icons.Default.Visibility
                                    },

                                contentDescription =
                                    if (passwordVisible) {
                                        "Hide password"
                                    } else {
                                        "Show password"
                                    },

                                modifier = Modifier.size(18.dp),
                                tint = Color(0xFF52688A)
                            )
                        }
                    },

                    visualTransformation =
                        if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },

                    singleLine = true,

                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 13.sp
                    ),

                    isError = passwordError.isNotEmpty(),

                    shape = RoundedCornerShape(10.dp),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,

                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,

                        focusedBorderColor = Color(0xFFE1E9F2),
                        unfocusedBorderColor = Color(0xFFE1E9F2)
                    )
                )


                if (passwordError.isNotEmpty()) {

                    Text(
                        text = passwordError,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 4.dp,
                                top = 4.dp
                            ),
                        fontSize = 11.sp,
                        color = Color.Red
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                // ---------------- CONFIRM PASSWORD ----------------

                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        confirmPasswordError = ""
                        generalError = ""
                    },

                    modifier = Modifier.fillMaxWidth(),

                    placeholder = {
                        Text(
                            text = "Confirm Password",
                            fontSize = 12.sp,
                            color = Color(0xFF52688A)
                        )
                    },

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Confirm Password",
                            modifier = Modifier.size(18.dp),
                            tint = Color(0xFF52688A)
                        )
                    },

                    trailingIcon = {

                        IconButton(
                            onClick = {
                                confirmPasswordVisible =
                                    !confirmPasswordVisible
                            },
                            modifier = Modifier.size(40.dp)
                        ) {

                            Icon(
                                imageVector =
                                    if (confirmPasswordVisible) {
                                        Icons.Default.VisibilityOff
                                    } else {
                                        Icons.Default.Visibility
                                    },

                                contentDescription =
                                    if (confirmPasswordVisible) {
                                        "Hide password"
                                    } else {
                                        "Show password"
                                    },

                                modifier = Modifier.size(18.dp),
                                tint = Color(0xFF52688A)
                            )
                        }
                    },

                    visualTransformation =
                        if (confirmPasswordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },

                    singleLine = true,

                    textStyle = LocalTextStyle.current.copy(
                        fontSize = 13.sp
                    ),

                    isError = confirmPasswordError.isNotEmpty(),

                    shape = RoundedCornerShape(10.dp),

                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,

                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,

                        focusedBorderColor = Color(0xFFE1E9F2),
                        unfocusedBorderColor = Color(0xFFE1E9F2)
                    )
                )


                if (confirmPasswordError.isNotEmpty()) {

                    Text(
                        text = confirmPasswordError,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 4.dp,
                                top = 4.dp
                            ),
                        fontSize = 11.sp,
                        color = Color.Red
                    )
                }


                Spacer(
                    modifier = Modifier.height(10.dp)
                )


                // ---------------- TERMS ----------------

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            termsAccepted = !termsAccepted
                            termsError = ""
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Checkbox(
                        checked = termsAccepted,
                        onCheckedChange = {
                            termsAccepted = it
                            termsError = ""
                        }
                    )

                    Text(
                        text = "I agree to the Terms and Conditions",
                        fontSize = 12.sp,
                        color = Color(0xFF52688A)
                    )
                }


                if (termsError.isNotEmpty()) {

                    Text(
                        text = termsError,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp),
                        fontSize = 11.sp,
                        color = Color.Red
                    )
                }


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                // ---------------- GENERAL FIREBASE ERROR ----------------

                if (generalError.isNotEmpty()) {

                    Text(
                        text = generalError,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Red
                    )
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                // ---------------- SIGN UP BUTTON ----------------

                val isLoading =
                    authState is AuthState.Loading


                Button(
                    onClick = {

                        // Clear previous errors
                        emailError = ""
                        passwordError = ""
                        confirmPasswordError = ""
                        termsError = ""
                        generalError = ""


                        // Email validation
                        if (email.isBlank()) {

                            emailError =
                                "Email is required"

                            return@Button
                        }


                        // Password validation
                        if (password.isBlank()) {

                            passwordError =
                                "Password is required"

                            return@Button
                        }


                        if (password.length < 6) {

                            passwordError =
                                "Password must be at least 6 characters"

                            return@Button
                        }


                        // Confirm password validation
                        if (confirmPassword.isBlank()) {

                            confirmPasswordError =
                                "Please confirm your password"

                            return@Button
                        }


                        if (password != confirmPassword) {

                            confirmPasswordError =
                                "Passwords do not match"

                            return@Button
                        }


                        // Terms validation
                        if (!termsAccepted) {

                            termsError =
                                "Please accept the Terms and Conditions"

                            return@Button
                        }


                        // Firebase registration
                        viewModel.register(
                            email = email,
                            password = password
                        )
                    },

                    enabled = !isLoading,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(47.dp),

                    shape = RoundedCornerShape(12.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2876D9)
                    )
                ) {

                    if (isLoading) {

                        CircularProgressIndicator(
                            modifier = Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )

                    } else {

                        Text(
                            text = "Sign Up",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(22.dp)
                )


                // ---------------- LOGIN LINK ----------------

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Already have an account? ",
                        fontSize = 12.sp,
                        color = Color(0xFF52688A)
                    )

                    Text(
                        text = "Login",
                        modifier = Modifier.clickable {

                            navController.popBackStack()
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2672D9)
                    )
                }
            }
        }
    }
}
