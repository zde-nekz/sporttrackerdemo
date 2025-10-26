package com.zdenekskrobak.sporttrackerdemo.training.domain

data class Training(
    val id: String,
    val name: String,
    val place: String,
    val duration: Int,
    val durationUnit: DurationUnit,
    val source: DataSource
)
