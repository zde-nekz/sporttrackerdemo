package com.zdenekskrobak.sporttrackerdemo.training.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zdenekskrobak.sporttrackerdemo.training.data.dto.TrainingEntity

@Database(
    entities = [TrainingEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trainingDao(): TrainingDao

    companion object {
        const val DATABASE_NAME = "app_database.db"
    }
}
