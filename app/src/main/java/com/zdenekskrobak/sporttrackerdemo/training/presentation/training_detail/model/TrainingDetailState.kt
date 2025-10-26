package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model

import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.DurationUnit

data class TrainingDetailState(
    val id: String? = null,
    val isLoading: Boolean = false,
    val name: String = "",
    val nameError: Boolean = false,
    val place: String = "",
    val placeError: Boolean = false,
    val duration: String = "",
    val durationError: Boolean = false,
    val durationUnit: DurationUnit = DurationUnit.SECOND,
    val source: DataSource = DataSource.PHONE,
)
