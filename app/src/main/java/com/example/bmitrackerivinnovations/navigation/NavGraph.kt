package com.example.bmitrackerivinnovations.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bmitrackerivinnovations.screens.BmiDetailScreen
import com.example.bmitrackerivinnovations.screens.BmiHistoryScreen
import com.example.bmitrackerivinnovations.screens.BmiResultScreen
import com.example.bmitrackerivinnovations.screens.ForgotPasswordScreen
import com.example.bmitrackerivinnovations.screens.LoginScreen
import com.example.bmitrackerivinnovations.screens.SignupScreen
import com.example.bmitrackerivinnovations.screens.WelcomeScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Routes.BMI_DETAIL_SCREEN,
        modifier = modifier
    ) {

        // --------------------------------------------------
        // WELCOME
        // --------------------------------------------------

        composable(
            Routes.WELCOME_SCREEN
        ) {

            WelcomeScreen(
                navController
            )
        }


        // --------------------------------------------------
        // LOGIN
        // --------------------------------------------------

        composable(
            Routes.LOGIN_SCREEN
        ) {

            LoginScreen(
                navController
            )
        }


        // --------------------------------------------------
        // SIGN UP
        // --------------------------------------------------

        composable(
            Routes.SIGNUP_SCREEN
        ) {

            SignupScreen(
                navController
            )
        }


        // --------------------------------------------------
        // FORGOT PASSWORD
        // --------------------------------------------------

        composable(
            Routes.FORGOT_SCREEN
        ) {

            ForgotPasswordScreen(
                navController
            )
        }


        // --------------------------------------------------
        // BMI DETAIL - NEW DATA
        // --------------------------------------------------

        composable(
            Routes.BMI_DETAIL_SCREEN
        ) {

            BmiDetailScreen(

                profileId = null,

                onSaved = { profileId ->

                    navController.navigate(
                        Routes.bmiResult(profileId)
                    )
                }
            )
        }


        // --------------------------------------------------
        // BMI RESULT
        // --------------------------------------------------

        composable(
            route = Routes.BMI_RESULT_SCREEN,

            arguments = listOf(
                navArgument("profileId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val profileId =
                backStackEntry
                    .arguments
                    ?.getInt("profileId")
                    ?: return@composable

            BmiResultScreen(

                profileId = profileId,

                onUpdate = {

                    navController.navigate(
                        Routes.bmiDetailEdit(
                            profileId
                        )
                    )
                },

                onHistory = {

                    navController.navigate(
                        Routes.bmiHistory(
                            profileId
                        )
                    )
                }
            )
        }


        // --------------------------------------------------
        // BMI DETAIL - UPDATE
        // --------------------------------------------------

        composable(
            route = Routes.BMI_DETAIL_EDIT_SCREEN,

            arguments = listOf(
                navArgument("profileId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val profileId =
                backStackEntry
                    .arguments
                    ?.getInt("profileId")
                    ?: return@composable

            BmiDetailScreen(

                profileId = profileId,

                onSaved = {

                    navController.popBackStack()
                }
            )
        }


        // --------------------------------------------------
        // BMI HISTORY
        // --------------------------------------------------

        composable(
            route = Routes.BMI_HISTORY_SCREEN,

            arguments = listOf(
                navArgument("profileId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val profileId =
                backStackEntry
                    .arguments
                    ?.getInt("profileId")
                    ?: return@composable

            BmiHistoryScreen(
                profileId = profileId
            )
        }
    }
}