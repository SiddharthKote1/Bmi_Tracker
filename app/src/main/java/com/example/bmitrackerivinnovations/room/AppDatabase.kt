package com.example.bmitrackerivinnovations.room

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bmitrackerivinnovations.history.BmiHistoryDao
import com.example.bmitrackerivinnovations.history.BmiHistoryEntity

@Database(
    entities = [
        UserProfileEntity::class,
        BmiHistoryEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao
    abstract fun bmiHistoryDao(): BmiHistoryDao


    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {

            return INSTANCE
                ?: synchronized(this) {

                    val instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "bmi_tracker_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()

                    INSTANCE = instance

                    instance
                }
        }
    }
}