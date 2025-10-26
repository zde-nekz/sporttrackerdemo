package com.zdenekskrobak.sporttrackerdemo.training.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trainings")
data class TrainingEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val place: String,
    val duration: Int,
    val durationUnit: String
)