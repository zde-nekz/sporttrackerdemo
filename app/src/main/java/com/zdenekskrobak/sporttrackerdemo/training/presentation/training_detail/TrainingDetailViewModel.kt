package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training
import com.zdenekskrobak.sporttrackerdemo.training.domain.TrainingRepository
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TrainingDetailViewModel(
    val id: String?,
    dataSource: DataSource?,
    val repository: TrainingRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TrainingDetailState())
    val state = _state.asStateFlow()

    private val _goBackEvent = Channel<Unit>()
    val goBackEvent = _goBackEvent.receiveAsFlow()

    init {
        if (id != null && dataSource != null) {
            loadTraining(id, dataSource)
        }
    }

    fun onAction(action: TrainingDetailAction) {
        when (action) {
            is TrainingDetailAction.OnNameChanged ->
                _state.update { it.copy(name = action.name) }

            is TrainingDetailAction.OnPlaceChanged ->
                _state.update { it.copy(place = action.place) }

            is TrainingDetailAction.OnDuration ->
                _state.update { it.copy(duration = action.duration) }

            is TrainingDetailAction.OnDurationUnitUnitChanged ->
                _state.update { it.copy(durationUnit = action.unit) }

            is TrainingDetailAction.OnSourceChanged ->
                _state.update { it.copy(source = action.value) }

            TrainingDetailAction.OnSaveClicked -> save()
            TrainingDetailAction.OnDeleteClicked -> delete()
            TrainingDetailAction.OnBackButtonClicked -> {

            }
        }
    }

    private fun loadTraining(id: String, dataSource: DataSource) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            if (dataSource == DataSource.PHONE) {
                val training = repository.findById(id)
                if (training != null) {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            id = training.id,
                            name = training.name,
                            place = training.place,
                            duration = training.duration.toString(),
                            durationUnit = training.durationUnit,
                            source = training.source
                        )
                    }
                }
            }
        }
    }

    private fun save() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    nameError = it.name.isBlank(),
                    placeError = it.place.isBlank(),
                    durationError = it.duration.isBlank()
                )
            }
            val state = _state.value

            if (state.nameError || state.placeError || state.durationError) {
                return@launch
            }

            _state.update {
                it.copy(isLoading = true)
            }

            repository.upsert(
                Training(
                    id = id ?: "",
                    name = state.name,
                    place = state.place,
                    duration = state.duration.toInt(),
                    durationUnit = state.durationUnit,
                    source = state.source
                )
            )
            _goBackEvent.trySend(Unit)
        }
    }

    private fun delete() {
        viewModelScope.launch {
            repository.deleteById(id!!)
            _goBackEvent.trySend(Unit)
        }
    }
}