package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdenekskrobak.sporttrackerdemo.training.data.repository.FirebaseTrainingRepository
import com.zdenekskrobak.sporttrackerdemo.training.data.repository.RoomTrainingRepository
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
    private val roomTrainingRepository: RoomTrainingRepository,
    private val firebaseTrainingRepository: FirebaseTrainingRepository
) : ViewModel() {

    private var observeLocalJob: Job? = null
    private var observeRemoteJob: Job? = null

    private val _state = MutableStateFlow(TrainingListState())
    val state = _state.asStateFlow()
        .onStart {
            observeLocalTrainings()
            observeRemoteTrainings()
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
        observeLocalJob?.cancel()
        observeLocalJob = roomTrainingRepository.getAll()
            .onEach { trainings ->
                _state.update {
                    it.copy(isLoading = false, localTrainings = trainings)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun observeRemoteTrainings() {
        observeRemoteJob?.cancel()
        observeRemoteJob = firebaseTrainingRepository.getAll()
            .onEach { trainings ->
                _state.update {
                    it.copy(isLoading = false, remoteTrainings = trainings)
                }
            }
            .launchIn(viewModelScope)
    }
}