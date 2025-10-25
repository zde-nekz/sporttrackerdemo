package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model

import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training

data class TrainingListState(
    val isLoading: Boolean = true,
    val trainings: List<Training> = emptyList(),
    val filter: DataSource? = null
)
