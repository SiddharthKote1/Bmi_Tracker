package com.example.bmitrackerivinnovations.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bmitrackerivinnovations.BmiCalculator
import com.example.bmitrackerivinnovations.history.BmiHistoryEntity
import com.example.bmitrackerivinnovations.repository.BmiRepository
import com.example.bmitrackerivinnovations.room.AppDatabase
import com.example.bmitrackerivinnovations.room.UserProfileEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BmiViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val database =
        AppDatabase.getDatabase(application)

    private val repository =
        BmiRepository(
            userProfileDao =
                database.userProfileDao(),

            bmiHistoryDao =
                database.bmiHistoryDao()
        )

    // --------------------------------------------------
    // CURRENT PROFILE
    // --------------------------------------------------

    private val _profile =
        MutableStateFlow<UserProfileEntity?>(null)

    val profile: StateFlow<UserProfileEntity?> =
        _profile.asStateFlow()

    // --------------------------------------------------
    // BMI HISTORY
    // --------------------------------------------------

    private val _bmiHistory =
        MutableStateFlow<List<BmiHistoryEntity>>(
            emptyList()
        )

    val bmiHistory:
            StateFlow<List<BmiHistoryEntity>> =
        _bmiHistory.asStateFlow()

    // --------------------------------------------------
    // SAVE PROFILE
    // --------------------------------------------------

    fun saveProfile(
        gender: String,
        heightCm: Double,
        weightKg: Double,
        onSuccess: (Int) -> Unit,
        onError: (String) -> Unit
    ) {

        if (gender.isBlank()) {
            onError("Please select gender")
            return
        }

        if (heightCm <= 0) {
            onError("Enter a valid height")
            return
        }

        if (weightKg <= 0) {
            onError("Enter a valid weight")
            return
        }

        viewModelScope.launch {

            try {

                val bmi =
                    BmiCalculator.calculate(
                        weightKg = weightKg,
                        heightCm = heightCm
                    )

                val profile =
                    UserProfileEntity(
                        gender = gender,
                        height = heightCm,
                        weight = weightKg,
                        bmi = bmi
                    )

                val profileId =
                    repository
                        .saveProfile(profile)
                        .toInt()

                repository.saveBmiHistory(
                    profileId = profileId,
                    bmi = bmi
                )

                _profile.value =
                    repository.getProfile(profileId)

                loadHistory(profileId)

                onSuccess(profileId)

            } catch (e: Exception) {

                onError(
                    e.message
                        ?: "Failed to save BMI data"
                )
            }
        }
    }

    // --------------------------------------------------
    // LOAD PROFILE
    // --------------------------------------------------

    fun loadProfile(
        profileId: Int
    ) {

        viewModelScope.launch {

            _profile.value =
                repository.getProfile(profileId)

            loadHistory(profileId)
        }
    }

    // --------------------------------------------------
    // UPDATE PROFILE
    // --------------------------------------------------

    fun updateProfile(
        profileId: Int,
        gender: String,
        heightCm: Double,
        weightKg: Double,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        if (gender.isBlank()) {
            onError("Please select gender")
            return
        }

        if (heightCm <= 0) {
            onError("Enter a valid height")
            return
        }

        if (weightKg <= 0) {
            onError("Enter a valid weight")
            return
        }

        viewModelScope.launch {

            try {

                val bmi =
                    BmiCalculator.calculate(
                        weightKg = weightKg,
                        heightCm = heightCm
                    )

                val updatedProfile =
                    UserProfileEntity(
                        id = profileId,
                        gender = gender,
                        height = heightCm,
                        weight = weightKg,
                        bmi = bmi
                    )

                repository.updateProfile(
                    updatedProfile
                )

                repository.saveBmiHistory(
                    profileId = profileId,
                    bmi = bmi
                )

                _profile.value =
                    updatedProfile

                loadHistory(profileId)

                onSuccess()

            } catch (e: Exception) {

                onError(
                    e.message
                        ?: "Failed to update BMI"
                )
            }
        }
    }

    // --------------------------------------------------
    // LAST 7 DAYS
    // --------------------------------------------------

    fun loadHistory(
        profileId: Int
    ) {

        viewModelScope.launch {

            val sevenDaysAgo =
                System.currentTimeMillis() -
                        (7L * 24L * 60L * 60L * 1000L)

            repository
                .observeBmiHistory(
                    profileId = profileId,
                    sevenDaysAgo = sevenDaysAgo
                )
                .collect { history ->

                    _bmiHistory.value =
                        history
                }
        }
    }
}