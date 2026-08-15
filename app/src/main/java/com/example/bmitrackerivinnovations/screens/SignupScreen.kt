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
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Password
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bmitrackerivinnovations.navigation.Routes

@Composable
fun SignupScreen(navController: NavController) {

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }

    var agreeToPrivacy by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FBFF))
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(65.dp))

        // Create Account
        Text(
            text = "Create Account",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(7.dp))

        // Subtitle
        Text(
            text = "Fill in your details to get started",
            fontSize = 13.sp,
            color = Color(0xFF52688A)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Full Name
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
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
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Full Name",
                    tint = Color(0xFF60708C),
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = "Full Name",
                    fontSize = 11.sp,
                    color = Color(0xFF52688A)
                )
            }
        }

        Spacer(modifier = Modifier.height(9.dp))

        // Email
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
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

        Spacer(modifier = Modifier.height(9.dp))

        // Password
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFFE1E9F2),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(start = 11.dp, end = 3.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Password,
                    contentDescription = "Password",
                    tint = Color(0xFF60708C),
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = "Password",
                    fontSize = 11.sp,
                    color = Color(0xFF52688A),
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.VisibilityOff,
                        contentDescription = "Show Password",
                        tint = Color(0xFF60708C),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(9.dp))

        // Confirm Password
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFFE1E9F2),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(start = 11.dp, end = 3.dp),
            contentAlignment = Alignment.CenterStart
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Lock,
                    contentDescription = "Confirm Password",
                    tint = Color(0xFF60708C),
                    modifier = Modifier.size(18.dp)
                )

                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = "Confirm Password",
                    fontSize = 11.sp,
                    color = Color(0xFF52688A),
                    modifier = Modifier.weight(1f)
                )

                IconButton(
                    onClick = {
                        confirmPasswordVisible = !confirmPasswordVisible
                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.VisibilityOff,
                        contentDescription = "Show Password",
                        tint = Color(0xFF60708C),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Privacy Policy
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = agreeToPrivacy,
                onCheckedChange = {
                    agreeToPrivacy = it
                },
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = "I agree to the ",
                fontSize = 11.sp,
                color = Color(0xFF52688A)
            )

            Text(
                text = "Privacy Policy",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2672D9)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Sign Up Button
        Button(
            onClick = {navController.navigate(Routes.FORGOT_SCREEN)},
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2876D9)
            )
        ) {

            Text(
                text = "Sign Up",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        // Login
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Already have an account? ",
                fontSize = 11.sp,
                color = Color(0xFF52688A)
            )

            Text(
                text = "Log In",
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2672D9)
            )
        }
    }
}