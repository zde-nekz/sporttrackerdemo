package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.zdenekskrobak.sporttrackerdemo.R

@Composable
fun DeleteButton(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { onDelete() },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
    ) {
        Text(stringResource(R.string.delete))
    }
}