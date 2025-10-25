package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
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
import androidx.compose.ui.unit.dp
import com.zdenekskrobak.sporttrackerdemo.R
import com.zdenekskrobak.sporttrackerdemo.training.domain.TimeUnit
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LengthField(state: TrainingDetailState,
                onAction: (TrainingDetailAction) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = state.length,
            onValueChange = { onAction(TrainingDetailAction.OnLengthChanged(it)) },
            label = { Text(stringResource(R.string.length)) },
            modifier = Modifier.weight(1f)
        )

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = state.lengthUnit.toString(),
                onValueChange = {},
                readOnly = true,
                label = { Text("Unit") },
                modifier = Modifier
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                    .width(80.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                TimeUnit.entries.forEach { unit ->
                    DropdownMenuItem(
                        text = { Text(unit.toString()) },
                        onClick = {
                            onAction(TrainingDetailAction.OnUnitChanged(unit))
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
