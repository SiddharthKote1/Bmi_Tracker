package com.example.bmitrackerivinnovations.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bmitrackerivinnovations.R
import com.example.bmitrackerivinnovations.navigation.Routes

@Composable
fun LoginScreen(navController: NavController) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FBFF))
            .padding(horizontal = 17.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(65.dp))

        // Welcome Back
        Text(
            text = "Welcome Back",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = "Login to continue",
            fontSize = 13.sp,
            color = Color(0xFF52688A)
        )

        Spacer(modifier = Modifier.height(28.dp))


        OutlinedButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(11.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White
            ),
            border = androidx.compose.foundation.BorderStroke(
                1.dp,
                Color(0xFFE2E9F2)
            )
        ) {

            // Google G
            Text(
                text = "G",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4285F4)
            )

            Spacer(modifier = Modifier.size(14.dp))

            Text(
                text = "Continue with Google",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF202124)
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // OR divider
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color(0xFFE3EAF2))
            )

            Text(
                text = "or",
                modifier = Modifier.padding(horizontal = 16.dp),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF52688A)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(1.dp)
                    .background(Color(0xFFE3EAF2))
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Email
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(11.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFFE1E9F2),
                    shape = RoundedCornerShape(11.dp)
                )
                .padding(horizontal = 11.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    modifier = Modifier.size(19.dp),
                    tint = Color(0xFF52688A)
                )

                Spacer(modifier = Modifier.size(11.dp))

                Text(
                    text = "Email address",
                    fontSize = 12.sp,
                    color = Color(0xFF52688A)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Password
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(11.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFFE1E9F2),
                    shape = RoundedCornerShape(11.dp)
                )
                .padding(start = 11.dp, end = 4.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password",
                    modifier = Modifier.size(19.dp),
                    tint = Color(0xFF52688A)
                )

                Spacer(modifier = Modifier.size(11.dp))

                Text(
                    text = "Password",
                    fontSize = 12.sp,
                    color = Color(0xFF52688A),
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {

                    Icon(
                        imageVector = if (passwordVisible) {
                            Icons.Default.VisibilityOff
                        } else {
                            Icons.Default.Visibility
                        },
                        contentDescription = if (passwordVisible) {
                            "Hide password"
                        } else {
                            "Show password"
                        },
                        modifier = Modifier.size(19.dp),
                        tint = Color(0xFF52688A)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(9.dp))

        // Forgot Password
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            Text(
                text = "Forgot Password?",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2672D9)
            )
        }

        Spacer(modifier = Modifier.height(54.dp))

        // Login button
        Button(
            onClick = {navController.navigate(Routes.SIGNUP_SCREEN)},
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2876D9)
            )
        ) {

            Text(
                text = "Log In",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Sign Up
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Don't have an account? ",
                fontSize = 12.sp,
                color = Color(0xFF52688A)
            )

            Text(
                text = "Sign Up",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2672D9)
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = NavController(context = LocalContext.current))
}