package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model

import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.TimeUnit

data class TrainingDetailState(
    val id: String? = null,
    val isLoading: Boolean = false,
    val name: String = "",
    val place: String = "",
    val length: String = "",
    val lengthUnit: TimeUnit = TimeUnit.SECOND,
    val source: DataSource = DataSource.DATABASE,
)
