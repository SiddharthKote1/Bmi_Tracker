package com.example.bmitrackerivinnovations.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_profiles"
)
data class UserProfileEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val gender: String,

    val height: Double,

    val weight: Double,

    val bmi: Double
)
