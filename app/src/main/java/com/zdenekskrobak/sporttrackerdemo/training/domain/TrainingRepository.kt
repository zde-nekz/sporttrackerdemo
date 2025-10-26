package com.zdenekskrobak.sporttrackerdemo.training.domain

import kotlinx.coroutines.flow.Flow

interface TrainingRepository {

    suspend fun upsert(training: Training)

    fun getAll(): Flow<List<Training>>

    suspend fun findById(id: String): Training?

    suspend fun deleteById(id: String)
}
