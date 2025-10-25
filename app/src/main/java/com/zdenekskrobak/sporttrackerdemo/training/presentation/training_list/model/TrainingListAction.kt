package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model

import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource

sealed interface TrainingListAction {

    data class Filter(val source: DataSource?) : TrainingListAction

    data class OpenDetail(val id: String, val source: DataSource) : TrainingListAction

    data object CreateTraining : TrainingListAction
}