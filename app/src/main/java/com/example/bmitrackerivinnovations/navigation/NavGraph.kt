package com.example.bmitrackerivinnovations.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bmitrackerivinnovations.screens.ForgotPasswordScreen
import com.example.bmitrackerivinnovations.screens.LoginScreen
import com.example.bmitrackerivinnovations.screens.SignupScreen
import com.example.bmitrackerivinnovations.screens.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController,modifier: Modifier) {

    NavHost(
        navController = navController,
        startDestination = Routes.WELCOME_SCREEN
    ) {
        composable(Routes.LOGIN_SCREEN) {
            LoginScreen(navController)
        }
        composable(Routes.WELCOME_SCREEN) {

            WelcomeScreen(navController)
        }
        composable(Routes.SIGNUP_SCREEN) {

            SignupScreen(navController)
        }
        composable(Routes.FORGOT_SCREEN){
            ForgotPasswordScreen(navController)
        }
    }
}