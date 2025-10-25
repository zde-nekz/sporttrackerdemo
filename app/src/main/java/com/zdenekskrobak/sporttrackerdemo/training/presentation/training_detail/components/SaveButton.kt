package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zdenekskrobak.sporttrackerdemo.R

@Composable
fun SaveButton(
    modifier: Modifier = Modifier,
    onSave: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { onSave() },
    ) {
        Text(stringResource(R.string.save))
    }
}