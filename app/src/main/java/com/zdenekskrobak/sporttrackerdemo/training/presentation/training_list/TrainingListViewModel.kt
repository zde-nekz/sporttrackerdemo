package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list

import androidx.lifecycle.ViewModel
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model.TrainingListAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model.TrainingListState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TrainingListViewModel(

) : ViewModel() {

    private val _state = MutableStateFlow(TrainingListState())
    val state = _state.asStateFlow()

    fun onAction(action: TrainingListAction) {
        when (action) {
            TrainingListAction.CreateTraining -> {

            }

            is TrainingListAction.Filter -> {
                _state.update {
                    it.copy(filter = action.source)
                }
            }

            is TrainingListAction.OpenDetail -> {
                TODO()
            }
        }
    }
}