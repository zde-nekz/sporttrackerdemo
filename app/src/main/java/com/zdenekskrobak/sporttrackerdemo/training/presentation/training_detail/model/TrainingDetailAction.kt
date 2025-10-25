package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model

import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.TimeUnit

sealed interface TrainingDetailAction {
    data class OnNameChanged(val name: String) : TrainingDetailAction
    data class OnPlaceChanged(val place: String) : TrainingDetailAction
    data class OnLengthChanged(val length: String) : TrainingDetailAction
    data class OnUnitChanged(val unit: TimeUnit) : TrainingDetailAction
    data class OnSourceChanged(val value: DataSource) : TrainingDetailAction
    data object OnBackButtonClicked: TrainingDetailAction
    data object OnSaveClicked : TrainingDetailAction
}