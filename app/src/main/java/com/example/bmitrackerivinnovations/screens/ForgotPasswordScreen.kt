package com.example.bmitrackerivinnovations.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bmitrackerivinnovations.R
import com.example.bmitrackerivinnovations.classes.AuthState
import com.example.bmitrackerivinnovations.viewmodel.AuthViewModel

@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: AuthViewModel = viewModel()
) {

    var email by remember {
        mutableStateOf("")
    }

    var emailError by remember {
        mutableStateOf("")
    }

    val authState by
    viewModel.authState.collectAsState()


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

                            viewModel.clearState()

                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.ArrowBack,
                            contentDescription =
                                "Back",
                            modifier =
                                Modifier.size(24.dp),
                            tint = Color.Black
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(100.dp)
                )


                // ---------------------------------------------------------
                // TITLE
                // ---------------------------------------------------------

                Text(
                    text = "Forgot Password",
                    fontSize = 20.sp,
                    fontWeight =
                        FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text =
                        "No worries! Enter your email and we'll",
                    fontSize = 13.sp,
                    fontWeight =
                        FontWeight.Medium,
                    color =
                        Color(0xFF52688A)
                )

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text =
                        "send you a reset link",
                    fontSize = 13.sp,
                    fontWeight =
                        FontWeight.Medium,
                    color =
                        Color(0xFF52688A)
                )


                Spacer(
                    modifier = Modifier.height(28.dp)
                )


                // ---------------------------------------------------------
                // IMAGE
                // ---------------------------------------------------------

                Image(
                    painter =
                        painterResource(
                            R.drawable.forgot
                        ),
                    contentDescription =
                        "Reset password",
                    modifier =
                        Modifier.size(200.dp)
                )


                // ---------------------------------------------------------
                // EMAIL
                // ---------------------------------------------------------

                OutlinedTextField(
                    value = email,

                    onValueChange = {

                        email = it
                        emailError = ""

                        if (authState !is AuthState.Idle) {
                            viewModel.clearState()
                        }
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    placeholder = {

                        Text(
                            text =
                                "Email address",
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


                // ---------------------------------------------------------
                // EMAIL ERROR
                // ---------------------------------------------------------

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
                    modifier = Modifier.height(20.dp)
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
                            .padding(
                                bottom = 10.dp
                            ),

                        fontSize = 12.sp,
                        fontWeight =
                            FontWeight.Medium,
                        color = Color.Red
                    )
                }


                // ---------------------------------------------------------
                // SUCCESS
                // ---------------------------------------------------------

                if (authState is AuthState.Success) {

                    Text(
                        text =
                            "Reset link sent. Check your inbox and Spam folder.",

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 10.dp
                            ),

                        fontSize = 12.sp,
                        fontWeight =
                            FontWeight.Medium,
                        color =
                            Color(0xFF2E7D32)
                    )
                }


                // ---------------------------------------------------------
                // SEND RESET LINK
                // ---------------------------------------------------------

                val isLoading =
                    authState is AuthState.Loading

                Button(
                    onClick = {

                        emailError = ""

                        if (email.isBlank()) {

                            emailError =
                                "Email is required"

                            return@Button
                        }

                        viewModel.resetPassword(
                            email = email.trim()
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
                            text =
                                "Send Reset Link",
                            fontSize = 15.sp,
                            fontWeight =
                                FontWeight.SemiBold,
                            color = Color.White
                        )
                    }
                }


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                // ---------------------------------------------------------
                // BACK TO LOGIN
                // ---------------------------------------------------------

                TextButton(
                    onClick = {

                        viewModel.clearState()

                        navController.popBackStack()
                    }
                ) {

                    Text(
                        text =
                            "Back to login",
                        color =
                            Color(0xFF2876D9),
                        fontWeight =
                            FontWeight.SemiBold
                    )
                }
            }
        }
    }
}