package com.example.bmitrackerivinnovations.repository

import com.example.bmitrackerivinnovations.history.BmiHistoryDao
import com.example.bmitrackerivinnovations.history.BmiHistoryEntity
import com.example.bmitrackerivinnovations.room.UserProfileDao
import com.example.bmitrackerivinnovations.room.UserProfileEntity
import kotlinx.coroutines.flow.Flow

class BmiRepository(
    private val userProfileDao: UserProfileDao,
    private val bmiHistoryDao: BmiHistoryDao
) {

    // --------------------------------------------------
    // PROFILE
    // --------------------------------------------------

    suspend fun saveProfile(
        profile: UserProfileEntity
    ): Long {

        return userProfileDao.insertUserProfile(
            profile
        )
    }

    suspend fun updateProfile(
        profile: UserProfileEntity
    ) {

        userProfileDao.updateUserProfile(
            profile
        )
    }

    suspend fun getProfile(
        profileId: Int
    ): UserProfileEntity? {

        return userProfileDao.getUserProfile(
            profileId
        )
    }

    // --------------------------------------------------
    // HISTORY
    // --------------------------------------------------

    suspend fun saveBmiHistory(
        profileId: Int,
        bmi: Double
    ) {

        bmiHistoryDao.insertBmiHistory(
            BmiHistoryEntity(
                profileId = profileId,
                bmi = bmi,
                date = System.currentTimeMillis()
            )
        )
    }

    fun observeBmiHistory(
        profileId: Int,
        sevenDaysAgo: Long
    ): Flow<List<BmiHistoryEntity>> {

        return bmiHistoryDao.observeBmiHistory(
            profileId = profileId,
            sevenDaysAgo = sevenDaysAgo
        )
    }

    // --------------------------------------------------
    // DELETE
    // --------------------------------------------------

    suspend fun deleteProfile(
        profile: UserProfileEntity
    ) {

        bmiHistoryDao.deleteHistory(
            profile.id
        )

        userProfileDao.deleteUserProfile(
            profile
        )
    }
}