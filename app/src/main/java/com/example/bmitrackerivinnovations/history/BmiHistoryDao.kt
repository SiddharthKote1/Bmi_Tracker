package com.example.bmitrackerivinnovations.history

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BmiHistoryDao {

    @Insert
    suspend fun insertBmiHistory(
        history: BmiHistoryEntity
    )

    @Query(
        """
        SELECT * FROM bmi_history
        WHERE profileId = :profileId
        AND date >= :sevenDaysAgo
        ORDER BY date ASC
        """
    )
    fun observeBmiHistory(
        profileId: Int,
        sevenDaysAgo: Long
    ): Flow<List<BmiHistoryEntity>>

    @Query(
        """
        DELETE FROM bmi_history
        WHERE profileId = :profileId
        """
    )
    suspend fun deleteHistory(
        profileId: Int
    )
}