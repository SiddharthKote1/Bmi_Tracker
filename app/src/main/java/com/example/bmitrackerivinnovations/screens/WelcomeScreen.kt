package com.example.bmitrackerivinnovations.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bmitrackerivinnovations.R
import com.example.bmitrackerivinnovations.navigation.Routes

@Composable
fun WelcomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 40.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Heart image
        Image(
            painter = painterResource(R.drawable.heart),
            contentDescription = "Heart",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))

        // BMI Tracker
        Text(
            text = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF2196F3)
                    )
                ) {
                    append("BMI")
                }

                withStyle(
                    style = SpanStyle(
                        color = Color.Black
                    )
                ) {
                    append(" Tracker")
                }
            },

            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Description
        Text(
            text = "Track your health,\nstay healthy stay fit",

            modifier = Modifier.fillMaxWidth(),

            textAlign = TextAlign.Center,

            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(R.drawable.run),
            contentDescription = "Healthy and stay fit",
            modifier = Modifier.size(220.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {navController.navigate(Routes.LOGIN_SCREEN)},

            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),

            shape = RoundedCornerShape(12.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {
            Text(
                text = "Get Started",
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(5.dp))

        // Login
        TextButton(
            onClick = {}
        ) {
            Text(
                text = buildAnnotatedString {

                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray
                        )
                    ) {
                        append("Already have an account? ")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF2196F3),
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Log in")
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(navController = NavController(context = LocalContext.current))
}