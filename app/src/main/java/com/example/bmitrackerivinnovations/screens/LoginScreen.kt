package com.example.bmitrackerivinnovations.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bmitrackerivinnovations.R
import com.example.bmitrackerivinnovations.classes.AuthState
import com.example.bmitrackerivinnovations.navigation.Routes
import com.example.bmitrackerivinnovations.viewmodel.AuthViewModel
import com.example.bmitrackerivinnovations.viewmodel.GoogleSignInHelper
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    val context = LocalContext.current

    val googleSignInHelper =
        remember {
            GoogleSignInHelper(context)
        }

    val lifecycleOwner =
        LocalLifecycleOwner.current

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var emailError by remember {
        mutableStateOf("")
    }

    var passwordError by remember {
        mutableStateOf("")
    }

    val authState by
    viewModel.authState.collectAsState()


    // ---------------------------------------------------------
    // AUTH STATE
    // ---------------------------------------------------------

    LaunchedEffect(authState) {

        when (authState) {

            // Firebase confirmed login
            is AuthState.Success -> {

                Toast.makeText(
                    context,
                    "Login successful",
                    Toast.LENGTH_SHORT
                ).show()

                viewModel.clearState()

                // IMPORTANT:
                // Replace HOME_SCREEN with your actual home route
                navController.navigate(
                    Routes.BMI_DETAIL_SCREEN
                ) {

                    popUpTo(
                        Routes.LOGIN_SCREEN
                    ) {
                        inclusive = true
                    }

                    launchSingleTop = true
                }
            }

            // Firebase rejected login
            is AuthState.Error -> {
                // Error is displayed below
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
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                // ---------------------------------------------------------
                // BACK BUTTON
                // ---------------------------------------------------------

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement =
                        Arrangement.Start
                ) {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier =
                                Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(28.dp)
                )


                // ---------------------------------------------------------
                // TITLE
                // ---------------------------------------------------------

                Text(
                    text = "Welcome Back",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Login to continue",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF52688A)
                )


                Spacer(
                    modifier = Modifier.height(28.dp)
                )


                // ---------------------------------------------------------
                // GOOGLE SIGN IN
                // ---------------------------------------------------------

                Button(
                    onClick = {

                        lifecycleOwner
                            .lifecycleScope
                            .launch {

                                googleSignInHelper
                                    .signInWithGoogle(

                                        onSuccess = {

                                            Toast.makeText(
                                                context,
                                                "Google Sign-In successful",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            navController.navigate(
                                                Routes.FORGOT_SCREEN
                                            ) {

                                                popUpTo(
                                                    Routes.LOGIN_SCREEN
                                                ) {
                                                    inclusive = true
                                                }

                                                launchSingleTop = true
                                            }
                                        },

                                        onError = { error ->

                                            Toast.makeText(
                                                context,
                                                error,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    )
                            }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),

                    shape =
                        RoundedCornerShape(11.dp),

                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor =
                                Color(0xFF202124)
                        ),

                    elevation =
                        ButtonDefaults.buttonElevation(
                            defaultElevation = 1.dp
                        )
                ) {

                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically,
                        horizontalArrangement =
                            Arrangement.Center
                    ) {

                        Image(
                            painter =
                                painterResource(
                                    R.drawable.google
                                ),
                            contentDescription =
                                "Google",
                            modifier =
                                Modifier.size(21.dp)
                        )

                        Spacer(
                            modifier =
                                Modifier.width(10.dp)
                        )

                        Text(
                            text =
                                "Continue with Google",
                            fontSize = 13.sp,
                            fontWeight =
                                FontWeight.SemiBold,
                            color =
                                Color(0xFF202124)
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(22.dp)
                )


                // ---------------------------------------------------------
                // OR
                // ---------------------------------------------------------

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(
                                Color(0xFFE3EAF2)
                            )
                    )

                    Text(
                        text = "or",
                        modifier =
                            Modifier.padding(
                                horizontal = 15.dp
                            ),
                        fontSize = 12.sp,
                        fontWeight =
                            FontWeight.SemiBold,
                        color =
                            Color(0xFF52688A)
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(1.dp)
                            .background(
                                Color(0xFFE3EAF2)
                            )
                    )
                }


                Spacer(
                    modifier = Modifier.height(22.dp)
                )


                // ---------------------------------------------------------
                // EMAIL
                // ---------------------------------------------------------

                OutlinedTextField(
                    value = email,

                    onValueChange = {
                        email = it
                        emailError = ""
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    placeholder = {
                        Text(
                            text = "Email address",
                            fontSize = 12.sp,
                            color =
                                Color(0xFF52688A)
                        )
                    },

                    leadingIcon = {
                        Icon(
                            imageVector =
                                Icons.Default.Email,
                            contentDescription =
                                "Email",
                            modifier =
                                Modifier.size(18.dp),
                            tint =
                                Color(0xFF52688A)
                        )
                    },

                    singleLine = true,

                    textStyle =
                        LocalTextStyle.current.copy(
                            fontSize = 13.sp
                        ),

                    isError =
                        emailError.isNotEmpty(),

                    shape =
                        RoundedCornerShape(10.dp),

                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedContainerColor =
                                Color.White,
                            unfocusedContainerColor =
                                Color.White,
                            focusedTextColor =
                                Color.Black,
                            unfocusedTextColor =
                                Color.Black,
                            focusedBorderColor =
                                Color(0xFFE1E9F2),
                            unfocusedBorderColor =
                                Color(0xFFE1E9F2)
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


                // ---------------------------------------------------------
                // PASSWORD
                // ---------------------------------------------------------

                OutlinedTextField(
                    value = password,

                    onValueChange = {
                        password = it
                        passwordError = ""
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    placeholder = {
                        Text(
                            text = "Password",
                            fontSize = 12.sp,
                            color =
                                Color(0xFF52688A)
                        )
                    },

                    leadingIcon = {

                        Icon(
                            imageVector =
                                Icons.Default.Lock,
                            contentDescription =
                                "Password",
                            modifier =
                                Modifier.size(18.dp),
                            tint =
                                Color(0xFF52688A)
                        )
                    },

                    trailingIcon = {

                        IconButton(
                            onClick = {

                                passwordVisible =
                                    !passwordVisible
                            }
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

                                modifier =
                                    Modifier.size(18.dp),

                                tint =
                                    Color(0xFF52688A)
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

                    textStyle =
                        LocalTextStyle.current.copy(
                            fontSize = 13.sp
                        ),

                    isError =
                        passwordError.isNotEmpty(),

                    shape =
                        RoundedCornerShape(10.dp),

                    colors =
                        OutlinedTextFieldDefaults.colors(
                            focusedContainerColor =
                                Color.White,
                            unfocusedContainerColor =
                                Color.White,
                            focusedTextColor =
                                Color.Black,
                            unfocusedTextColor =
                                Color.Black,
                            focusedBorderColor =
                                Color(0xFFE1E9F2),
                            unfocusedBorderColor =
                                Color(0xFFE1E9F2)
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
                    modifier = Modifier.height(9.dp)
                )


                // ---------------------------------------------------------
                // FORGOT PASSWORD
                // ---------------------------------------------------------

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),
                    horizontalArrangement =
                        Arrangement.End
                ) {

                    TextButton(
                        onClick = {

                            navController.navigate(
                                Routes.FORGOT_SCREEN
                            )
                        },

                        contentPadding =
                            PaddingValues(0.dp)
                    ) {

                        Text(
                            text =
                                "Forgot Password?",
                            fontSize = 12.sp,
                            fontWeight =
                                FontWeight.SemiBold,
                            color =
                                Color(0xFF2672D9)
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(35.dp)
                )


                // ---------------------------------------------------------
                // FIREBASE ERROR
                // ---------------------------------------------------------

                if (authState is AuthState.Error) {

                    Text(
                        text =
                            (authState as AuthState.Error)
                                .message,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),

                        fontSize = 12.sp,
                        fontWeight =
                            FontWeight.Medium,
                        color = Color.Red
                    )
                }


                // ---------------------------------------------------------
                // LOGIN BUTTON
                // ---------------------------------------------------------

                val isLoading =
                    authState is AuthState.Loading

                Button(
                    onClick = {

                        emailError = ""
                        passwordError = ""

                        if (email.isBlank()) {

                            emailError =
                                "Email is required"

                            return@Button
                        }

                        if (password.isBlank()) {

                            passwordError =
                                "Password is required"

                            return@Button
                        }

                        viewModel.login(
                            email = email.trim(),
                            password = password
                        )
                    },

                    enabled = !isLoading,

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(47.dp),

                    shape =
                        RoundedCornerShape(12.dp),

                    colors =
                        ButtonDefaults.buttonColors(
                            containerColor =
                                Color(0xFF2876D9)
                        )
                ) {

                    if (isLoading) {

                        CircularProgressIndicator(
                            modifier =
                                Modifier.size(20.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )

                    } else {

                        Text(
                            text = "Log In",
                            fontSize = 15.sp,
                            fontWeight =
                                FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(22.dp)
                )


                // ---------------------------------------------------------
                // SIGN UP
                // ---------------------------------------------------------

                Row(
                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text =
                            "Don't have an account? ",
                        fontSize = 12.sp,
                        color =
                            Color(0xFF52688A)
                    )

                    TextButton(
                        onClick = {

                            navController.navigate(
                                Routes.SIGNUP_SCREEN
                            )
                        },

                        contentPadding =
                            PaddingValues(0.dp)
                    ) {

                        Text(
                            text = "Sign Up",
                            fontSize = 12.sp,
                            fontWeight =
                                FontWeight.Bold,
                            color =
                                Color(0xFF2672D9)
                        )
                    }
                }
            }
        }
    }
}
