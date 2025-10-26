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
fun NameField(
    state: TrainingDetailState,
    onNameChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.name,
        onValueChange = { onNameChanged(it) },
        label = { Text(stringResource(R.string.name)) },
        isError = state.nameError,
        supportingText = {
            if (state.nameError) {
                Text(
                    text = stringResource(R.string.error_empty),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    )
}