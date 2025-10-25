package com.zdenekskrobak.sporttrackerdemo.training.domain

data class Training(
    val id: String,
    val name: String,
    val place: String,
    val length: String,
    val source: DataSource
)
