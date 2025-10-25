package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TrainingDetailViewModel : ViewModel() {

    private val _state = MutableStateFlow(TrainingDetailState())
    val state = _state.asStateFlow()

    fun onAction(action: TrainingDetailAction) {
        when (action) {
            is TrainingDetailAction.OnNameChanged ->
                _state.value = _state.value.copy(name = action.name)
            is TrainingDetailAction.OnPlaceChanged ->
                _state.value = _state.value.copy(place = action.place)
            is TrainingDetailAction.OnLengthChanged ->
                _state.value = _state.value.copy(length = action.length)
            is TrainingDetailAction.OnUnitChanged ->
                _state.value = _state.value.copy(lengthUnit = action.unit)
            is TrainingDetailAction.OnSourceChanged ->
                _state.value = _state.value.copy(source = action.value)
            TrainingDetailAction.OnSaveClicked -> save()
            TrainingDetailAction.OnBackButtonClicked -> {

            }
        }
    }

    private fun save() {
        viewModelScope.launch {

        }
    }
}