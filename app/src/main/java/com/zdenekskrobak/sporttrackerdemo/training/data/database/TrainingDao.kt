package com.zdenekskrobak.sporttrackerdemo.training.data.database

import androidx.room.*
import com.zdenekskrobak.sporttrackerdemo.training.data.dto.TrainingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {

    @Upsert
    suspend fun upsert(training: TrainingEntity)

    @Query("SELECT * FROM trainings")
    fun getAll(): Flow<List<TrainingEntity>>

    @Query("SELECT * FROM trainings WHERE id = :id")
    suspend fun findById(id: String): TrainingEntity?

    @Delete
    suspend fun delete(training: TrainingEntity)

    @Query("DELETE FROM trainings WHERE id = :id")
    suspend fun deleteById(id: String)
}
