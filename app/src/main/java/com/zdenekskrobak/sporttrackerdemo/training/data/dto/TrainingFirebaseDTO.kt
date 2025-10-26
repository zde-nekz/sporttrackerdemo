package com.zdenekskrobak.sporttrackerdemo.training.data.dto

import com.zdenekskrobak.sporttrackerdemo.training.domain.DurationUnit

data class TrainingFirebaseDTO(
    val id: String = "",
    val name: String = "",
    val place: String = "",
    val duration: Int = 0,
    val durationUnit: String = DurationUnit.SECOND.name
)