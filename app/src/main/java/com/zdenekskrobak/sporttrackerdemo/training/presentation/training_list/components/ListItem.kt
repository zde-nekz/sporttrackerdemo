package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zdenekskrobak.sporttrackerdemo.R
import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.DurationUnit
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training
import com.zdenekskrobak.sporttrackerdemo.training.domain.format
import com.zdenekskrobak.sporttrackerdemo.ui.theme.Pink40
import com.zdenekskrobak.sporttrackerdemo.ui.theme.Purple40

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    training: Training,
    onClicked: (Training) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClicked(training)
            }

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = training.name, style = MaterialTheme.typography.headlineMedium)

                Icon(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .size(12.dp),
                    imageVector = Icons.Default.Storage.takeIf { training.source == DataSource.PHONE }
                        ?: Icons.Default.Cloud,
                    contentDescription = stringResource(R.string.filter_phone.takeIf { training.source == DataSource.PHONE }
                        ?: R.string.filter_cloud)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = stringResource(R.string.place),
                    tint = Purple40
                )
                Text(text = training.place, style = MaterialTheme.typography.bodyMedium)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Timer,
                    contentDescription = stringResource(R.string.duration),
                    tint = Pink40
                )
                Text(
                    text = "${training.duration}${training.durationUnit.format()}",
                    style = MaterialTheme.typography.bodyMedium
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
            duration = 12,
            durationUnit = DurationUnit.MINUTE,
            source = DataSource.PHONE
        ), onClicked = {})
}