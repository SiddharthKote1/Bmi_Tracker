package com.example.bmitrackerivinnovations.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bmitrackerivinnovations.viewmodel.BmiViewModel

@Composable
fun BmiDetailScreen(
    profileId: Int? = null,
    onSaved: (Int) -> Unit,
    viewModel: BmiViewModel = viewModel()
) {

    val context = LocalContext.current

    val profile by
    viewModel.profile
        .collectAsStateWithLifecycle()

    var gender by remember {
        mutableStateOf("")
    }

    var height by remember {
        mutableStateOf("")
    }

    var weight by remember {
        mutableStateOf("")
    }

    // Load existing data when editing
    LaunchedEffect(profileId) {

        if (profileId != null) {
            viewModel.loadProfile(profileId)
        }
    }

    // Fill fields with existing data
    LaunchedEffect(profile) {

        profile?.let {

            gender = it.gender
            height = it.height.toString()
            weight = it.weight.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Top
    ) {

        Text(
            text =
                if (profileId == null)
                    "Enter Your Details"
                else
                    "Update Your Details"
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Gender"
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Row(
                modifier = Modifier.weight(1f)
            ) {

                RadioButton(
                    selected =
                        gender == "Male",

                    onClick = {
                        gender = "Male"
                    }
                )

                Text(
                    text = "Male",
                    modifier = Modifier.padding(
                        top = 12.dp
                    )
                )
            }

            Row(
                modifier = Modifier.weight(1f)
            ) {

                RadioButton(
                    selected =
                        gender == "Female",

                    onClick = {
                        gender = "Female"
                    }
                )

                Text(
                    text = "Female",
                    modifier = Modifier.padding(
                        top = 12.dp
                    )
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = height,

            onValueChange = {
                height = it
            },

            label = {
                Text("Height (cm)")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = weight,

            onValueChange = {
                weight = it
            },

            label = {
                Text("Weight (kg)")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {

                if (gender.isBlank()) {

                    Toast.makeText(
                        context,
                        "Please select gender",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                val heightValue =
                    height.toDoubleOrNull()

                val weightValue =
                    weight.toDoubleOrNull()

                if (
                    heightValue == null ||
                    heightValue <= 0
                ) {

                    Toast.makeText(
                        context,
                        "Enter a valid height",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if (
                    weightValue == null ||
                    weightValue <= 0
                ) {

                    Toast.makeText(
                        context,
                        "Enter a valid weight",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@Button
                }

                if (profileId == null) {

                    viewModel.saveProfile(

                        gender = gender,

                        heightCm =
                            heightValue,

                        weightKg =
                            weightValue,

                        onSuccess = { id ->
                            onSaved(id)
                        },

                        onError = { error ->

                            Toast.makeText(
                                context,
                                error,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )

                } else {

                    viewModel.updateProfile(

                        profileId =
                            profileId,

                        gender =
                            gender,

                        heightCm =
                            heightValue,

                        weightKg =
                            weightValue,

                        onSuccess = {
                            onSaved(profileId)
                        },

                        onError = { error ->

                            Toast.makeText(
                                context,
                                error,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                }
            },

            modifier =
                Modifier.fillMaxWidth()
        ) {

            Text(
                text =
                    if (profileId == null)
                        "Calculate BMI"
                    else
                        "Update BMI"
            )
        }
    }
}