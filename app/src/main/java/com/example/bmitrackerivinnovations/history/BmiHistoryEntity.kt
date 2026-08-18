package com.example.bmitrackerivinnovations.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bmi_history"
)
data class BmiHistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val profileId: Int,

    val bmi: Double,

    val date: Long
)