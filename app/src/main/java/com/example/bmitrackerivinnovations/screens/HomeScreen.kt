package com.example.bmitrackerivinnovations.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmitrackerivinnovations.R

@Composable
fun LoginScreen() {

    val primaryPurple = Color(0xFF6C4DF6)
    val secondaryPurple = Color(0xFF8B5CF6)
    val lightPurple = Color(0xFFF4F0FF)
    val darkText = Color(0xFF171321)
    val grayText = Color(0xFF77727F)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF9F7FF),
                        Color.White,
                        Color.White
                    )
                )
            )
    ) {

        // Decorative background circle - top right
        Box(
            modifier = Modifier
                .offset(x = 240.dp, y = (-70).dp)
                .size(180.dp)
                .clip(CircleShape)
                .background(
                    Color(0xFFEAE3FF)
                )
        )

        // Decorative background circle - bottom left
        Box(
            modifier = Modifier
                .offset(x = (-80).dp, y = 620.dp)
                .size(170.dp)
                .clip(CircleShape)
                .background(
                    Color(0xFFF0EBFF)
                )
        )

        // Back button
        Box(
            modifier = Modifier
                .statusBarsPadding()
                .padding(
                    start = 20.dp,
                    top = 16.dp
                )
                .size(44.dp)
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(14.dp)
                )
                .clip(RoundedCornerShape(14.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(
                    id = R.drawable.arrowback
                ),
                contentDescription = null,
                tint = darkText,
                modifier = Modifier.size(21.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp)
                .statusBarsPadding()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(90.dp)
            )

            // App visual
            Box(
                modifier = Modifier
                    .size(105.dp)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clip(RoundedCornerShape(30.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                primaryPurple,
                                secondaryPurple
                            )
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {

                // Document shape
                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(62.dp)
                        .clip(RoundedCornerShape(7.dp))
                        .background(Color.White)
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                horizontal = 9.dp,
                                vertical = 12.dp
                            ),
                        verticalArrangement = Arrangement.spacedBy(7.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .width(22.dp)
                                .height(4.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(primaryPurple)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFFD8D2E8))
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(4.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFFD8D2E8))
                        )

                        Box(
                            modifier = Modifier
                                .width(25.dp)
                                .height(4.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(Color(0xFFD8D2E8))
                        )
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // Heading
            Text(
                text = "Welcome to\nBMI Tracker",
                color = darkText,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 36.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Log in or create an account to continue",
                color = grayText,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(40.dp)
            )

            // Google button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE5E1EB),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Google G
                    Text(
                        text = "G",
                        color = Color(0xFF4285F4),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.width(12.dp)
                    )

                    Text(
                        text = "Continue with Google",
                        color = darkText,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // Divider
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color(0xFFE8E4EC))
                )

                Text(
                    text = "  OR  ",
                    color = grayText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Color(0xFFE8E4EC))
                )
            }

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            // Guest button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(lightPurple)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE2D9FF),
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "Continue as Guest",
                    color = primaryPurple,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            // Bottom text
            Text(
                text = "Your data stays private and secure",
                color = Color(0xFF9993A2),
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(24.dp)
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
    LoginScreen()
}