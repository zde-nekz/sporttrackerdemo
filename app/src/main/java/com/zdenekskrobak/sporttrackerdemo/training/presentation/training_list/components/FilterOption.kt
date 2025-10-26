package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.zdenekskrobak.sporttrackerdemo.R
import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.ui.theme.PurpleGrey80
import com.zdenekskrobak.sporttrackerdemo.ui.theme.White

@Composable
fun FilterOption(
    modifier: Modifier = Modifier,
    text: String,
    source: DataSource? = null,
    isSelected: Boolean,
    onClicked: (DataSource?) -> Unit = {}
) {
    FilterChip(
        modifier = modifier,
        colors = FilterChipDefaults.filterChipColors(
            containerColor = PurpleGrey80,
            selectedContainerColor = White,
            selectedLabelColor = Color.Black,
            labelColor = Color.DarkGray
        ),
        label = {
            Text(
                text,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        selected = isSelected,
        onClick = {
            onClicked(source)
        }
    )
}

@Preview
@Composable
private fun FilterChipPreview() {
    FilterOption(
        text = stringResource(R.string.filter_phone),
        isSelected = false
    )
}

@Preview
@Composable
private fun FilterChipPreviewSelected() {
    FilterOption(text = stringResource(R.string.filter_phone), isSelected = true)
}