package com.example.bmitrackerivinnovations.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bmitrackerivinnovations.screens.LoginScreen

@Composable
fun NavGraph(navController: NavHostController,modifier: Modifier) {

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN_SCREEN
    ) {
        composable(Routes.LOGIN_SCREEN) {
            LoginScreen()
        }
    }
}