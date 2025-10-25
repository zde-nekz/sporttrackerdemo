package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    training: Training,
    onClicked: (Training) -> Unit
) {
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable {
            onClicked(training)
        }) {
        Column {
            Row {
                Text(text = training.name, style = MaterialTheme.typography.bodyMedium)

                Icon(
                    imageVector = Icons.Default.De
                )
            }
        }
    }
}

@Preview
@Composable
private fun ListItemPreview() {
    ListItem(
        training = Training(
            id = "",
            name = "Run",
            place = "Stromovka",
            length = "12",
            source = DataSource.DATABASE
        ), onClicked = {})
}