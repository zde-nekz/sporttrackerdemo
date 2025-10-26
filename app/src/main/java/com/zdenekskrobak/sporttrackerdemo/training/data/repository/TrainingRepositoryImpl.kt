package com.zdenekskrobak.sporttrackerdemo.training.data.repository

import com.zdenekskrobak.sporttrackerdemo.training.data.database.TrainingDao
import com.zdenekskrobak.sporttrackerdemo.training.data.mappers.toDomain
import com.zdenekskrobak.sporttrackerdemo.training.data.mappers.toEntity
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training
import com.zdenekskrobak.sporttrackerdemo.training.domain.TrainingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TrainingRepositoryImpl(
    private val trainingDao: TrainingDao
) : TrainingRepository {

    override suspend fun upsert(training: Training) {
        trainingDao.upsert(training.toEntity())
    }

    override fun getAll(): Flow<List<Training>> {
        return trainingDao.getAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun findById(id: String): Training? {
        return trainingDao.findById(id)?.toDomain()
    }

    override suspend fun deleteById(id: String) {
        trainingDao.deleteById(id)
    }
}
