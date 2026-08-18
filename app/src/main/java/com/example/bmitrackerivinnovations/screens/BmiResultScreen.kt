package com.example.bmitrackerivinnovations.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmitrackerivinnovations.BmiCalculator
import com.example.bmitrackerivinnovations.viewmodel.BmiViewModel

@Composable
fun BmiResultScreen(
    profileId: Int,
    onUpdate: () -> Unit,
    onHistory: () -> Unit,
    viewModel: BmiViewModel = viewModel()
) {

    val profile by
    viewModel.profile
        .collectAsStateWithLifecycle()

    LaunchedEffect(profileId) {

        viewModel.loadProfile(profileId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        profile?.let { data ->

            Text(
                text = "Your BMI"
            )

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            Text(
                text =
                    String.format(
                        "%.1f",
                        data.bmi
                    ),

                fontWeight =
                    FontWeight.Bold
            )

            Spacer(
                modifier =
                    Modifier.height(8.dp)
            )

            Text(
                text =
                    BmiCalculator.getCategory(
                        data.bmi
                    )
            )

            Spacer(
                modifier =
                    Modifier.height(24.dp)
            )

            Text(
                text =
                    "Gender: ${data.gender}"
            )

            Text(
                text =
                    "Height: ${data.height} cm"
            )

            Text(
                text =
                    "Weight: ${data.weight} kg"
            )

            Spacer(
                modifier =
                    Modifier.height(32.dp)
            )

            Button(
                onClick = onUpdate,

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        "Update Height / Weight"
                )
            }

            Spacer(
                modifier =
                    Modifier.height(12.dp)
            )

            Button(
                onClick = onHistory,

                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Text(
                    text =
                        "BMI History"
                )
            }
        }
    }
}