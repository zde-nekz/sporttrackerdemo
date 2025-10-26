package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zdenekskrobak.sporttrackerdemo.R
import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.format
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SourceDropdown(
    state: TrainingDetailState,
    onSourceChanged: (DataSource) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val sources = DataSource.entries

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = state.source.format(),
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.where_save)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable, true)
                .fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            sources.forEach { source ->
                DropdownMenuItem(
                    text = { Text(source.format()) },
                    enabled = state.id == null,
                    onClick = {
                        onSourceChanged(source)
                        expanded = false
                    }
                )
            }
        }
    }
}
