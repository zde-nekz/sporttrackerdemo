package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model

import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training

data class TrainingListState(
    val isLoading: Boolean = true,
    val localTrainings: List<Training> = emptyList(),
    val remoteTrainings: List<Training> = emptyList(),
    val filter: DataSource? = null
) {
    val trainings = when (filter) {
        DataSource.PHONE -> localTrainings
        DataSource.CLOUD -> remoteTrainings
        else -> localTrainings + remoteTrainings.sortedBy { it.id }
    }
}
