package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.zdenekskrobak.sporttrackerdemo.R
import com.zdenekskrobak.sporttrackerdemo.training.domain.DurationUnit
import com.zdenekskrobak.sporttrackerdemo.training.domain.format
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DurationField(
    state: TrainingDetailState,
    onAction: (TrainingDetailAction) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = state.duration,
            onValueChange = { text ->
                if (text.isDigitsOnly()) {
                    onAction(TrainingDetailAction.OnDuration(text))
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.duration)) },
            modifier = Modifier.weight(1f),
            isError = state.durationError,
            supportingText = {
                if (state.durationError) {
                    Text(
                        text = stringResource(R.string.error_empty),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        )

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = state.durationUnit.format(),
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                    .width(80.dp),
                supportingText = {}
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DurationUnit.entries.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(unit.format()) },
                        onClick = {
                            onAction(TrainingDetailAction.OnDurationUnitUnitChanged(unit))
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
