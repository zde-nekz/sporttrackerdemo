package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdenekskrobak.sporttrackerdemo.training.domain.TrainingRepository
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model.TrainingListAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model.TrainingListState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class TrainingListViewModel(
    private val trainingRepository: TrainingRepository
) : ViewModel() {

    private var observeJob: Job? = null

    private val _state = MutableStateFlow(TrainingListState())
    val state = _state.asStateFlow()
        .onStart {
            observeLocalTrainings()
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000), _state.value
        )

    fun onAction(action: TrainingListAction) {
        when (action) {
            is TrainingListAction.Filter -> {
                _state.update {
                    it.copy(filter = action.source)
                }
            }

            else -> {}
        }
    }

    private fun observeLocalTrainings() {
        observeJob?.cancel()
        observeJob = trainingRepository.getAll()
            .onEach { trainings ->
                _state.update {
                    it.copy(isLoading = false, localTrainings = trainings)
                }
            }
            .launchIn(viewModelScope)
    }
}