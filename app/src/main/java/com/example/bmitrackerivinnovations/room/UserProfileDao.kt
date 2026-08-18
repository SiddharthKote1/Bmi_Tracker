package com.example.bmitrackerivinnovations.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {

    // Save a new user profile
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserProfile(
        userProfile: UserProfileEntity
    ): Long


    // Update an existing profile
    @Update
    suspend fun updateUserProfile(
        userProfile: UserProfileEntity
    )


    // Get one profile
    @Query(
        """
        SELECT * FROM user_profiles
        WHERE id = :profileId
        LIMIT 1
        """
    )
    suspend fun getUserProfile(
        profileId: Int
    ): UserProfileEntity?


    // Observe one profile
    @Query(
        """
        SELECT * FROM user_profiles
        WHERE id = :profileId
        LIMIT 1
        """
    )
    fun observeUserProfile(
        profileId: Int
    ): Flow<UserProfileEntity?>


    // Get all profiles
    @Query(
        """
        SELECT * FROM user_profiles
        ORDER BY id ASC
        """
    )
    fun observeAllProfiles():
            Flow<List<UserProfileEntity>>


    // Delete one profile
    @Delete
    suspend fun deleteUserProfile(
        userProfile: UserProfileEntity
    )


    // Delete all profiles
    @Query("DELETE FROM user_profiles")
    suspend fun deleteAllProfiles()
}