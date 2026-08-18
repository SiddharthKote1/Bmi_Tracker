package com.example.bmitrackerivinnovations

object BmiCalculator {

    fun calculate(
        weightKg: Double,
        heightCm: Double
    ): Double {

        if (
            weightKg <= 0 ||
            heightCm <= 0
        ) {
            return 0.0
        }

        val heightMeters =
            heightCm / 100.0

        return weightKg /
                (heightMeters * heightMeters)
    }


    fun getCategory(
        bmi: Double
    ): String {

        return when {

            bmi < 18.5 ->
                "Underweight"

            bmi < 25.0 ->
                "Normal weight"

            bmi < 30.0 ->
                "Overweight"

            else ->
                "Obese"
        }
    }
}