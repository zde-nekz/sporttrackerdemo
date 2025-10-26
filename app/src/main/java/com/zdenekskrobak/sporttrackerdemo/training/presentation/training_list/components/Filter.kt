package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.zdenekskrobak.sporttrackerdemo.ui.theme.Purple40
import com.zdenekskrobak.sporttrackerdemo.ui.theme.White

@Composable
fun Filter(
    selectedSource: DataSource?,
    onSelected: (DataSource?) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Purple40)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.filter),
            style = MaterialTheme.typography.headlineSmall,
            color = White
        )

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            FilterOption(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.filter_all),
                source = null,
                isSelected = selectedSource == null,
                onClicked = {
                    onSelected(null)
                },
            )

            FilterOption(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.filter_phone),
                source = DataSource.PHONE,
                isSelected = selectedSource == DataSource.PHONE,
                onClicked = {
                    onSelected(DataSource.PHONE)
                },
            )

            FilterOption(
                modifier = Modifier.weight(1f),
                text = stringResource(R.string.filter_cloud),
                source = DataSource.CLOUD,
                isSelected = selectedSource == DataSource.CLOUD,
                onClicked = {
                    onSelected(DataSource.CLOUD)
                },
            )

        }
    }

}

@Preview
@Composable
private fun FilterPreview() {
    Filter(selectedSource = null, onSelected = {})
}