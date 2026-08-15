package com.example.bmitrackerivinnovations.screens

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
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ForgotPasswordScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FBFF))
            .padding(horizontal = 3.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Back arrow
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "‹",
                fontSize = 38.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 5.dp)
            )
        }

        Spacer(modifier = Modifier.height(34.dp))

        // Title
        Text(
            text = "Forgot Password?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(7.dp))

        // Description
        Text(
            text = "No worries! Enter your email and we'll",
            fontSize = 12.sp,
            color = Color(0xFF52688A)
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = "send you a reset link.",
            fontSize = 12.sp,
            color = Color(0xFF52688A)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Illustration placeholder
        Box(
            modifier = Modifier
                .size(145.dp)
                .background(
                    color = Color(0xFFEAF4FF),
                    shape = RoundedCornerShape(100.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "✉",
                fontSize = 55.sp,
                color = Color(0xFF2876D9)
            )
        }

        Spacer(modifier = Modifier.height(17.dp))

        // Email field
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(43.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFFE1E9F2),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 11.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Email",
                    tint = Color(0xFF60708C),
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = "Email address",
                    fontSize = 11.sp,
                    color = Color(0xFF52688A)
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))

        // Send Reset Link button
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp),
            shape = RoundedCornerShape(11.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2876D9)
            )
        ) {

            Text(
                text = "Send Reset Link",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = "Back to Login",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2672D9)
        )
    }
}