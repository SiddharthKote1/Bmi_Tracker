package com.example.bmitrackerivinnovations.navigation

object Routes {

    const val WELCOME_SCREEN = "WelcomeScreen"

    const val LOGIN_SCREEN = "LoginScreen"

    const val SIGNUP_SCREEN = "SignupScreen"

    const val FORGOT_SCREEN = "ForgotPasswordScreen"

    // BMI
    const val BMI_DETAIL_SCREEN = "BmiDetailScreen"

    const val BMI_RESULT_SCREEN = "BmiResultScreen/{profileId}"

    const val BMI_HISTORY_SCREEN = "BmiHistoryScreen/{profileId}"

    const val BMI_DETAIL_EDIT_SCREEN = "BmiDetailEditScreen/{profileId}"

    fun bmiResult(profileId: Int): String {
        return "BmiResultScreen/$profileId"
    }

    fun bmiHistory(profileId: Int): String {
        return "BmiHistoryScreen/$profileId"
    }

    fun bmiDetailEdit(profileId: Int): String {
        return "BmiDetailEditScreen/$profileId"
    }
}