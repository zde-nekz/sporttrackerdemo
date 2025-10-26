package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model

import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.DurationUnit

sealed interface TrainingDetailAction {
    data class OnNameChanged(val name: String) : TrainingDetailAction
    data class OnPlaceChanged(val place: String) : TrainingDetailAction
    data class OnDuration(val duration: String) : TrainingDetailAction
    data class OnDurationUnitUnitChanged(val unit: DurationUnit) : TrainingDetailAction
    data class OnSourceChanged(val value: DataSource) : TrainingDetailAction
    data object OnBackButtonClicked: TrainingDetailAction
    data object OnSaveClicked : TrainingDetailAction
    data object OnDeleteClicked: TrainingDetailAction
}