package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zdenekskrobak.sporttrackerdemo.R
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailState

@Composable
fun PlaceField(
    state: TrainingDetailState,
    onPlaceChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.place,
        onValueChange = { onPlaceChanged(it) },
        label = { Text(stringResource(R.string.place)) },
        isError = state.placeError,
        supportingText = {
            if (state.placeError) {
                Text(
                    text = stringResource(R.string.error_empty),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}