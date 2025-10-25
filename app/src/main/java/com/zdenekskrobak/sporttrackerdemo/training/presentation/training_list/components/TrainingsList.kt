package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model.TrainingListAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model.TrainingListState

@Composable
fun TrainingsList(
    state: TrainingListState,
    onAction: (TrainingListAction) -> Unit
) {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    if (isPortrait) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.trainings.size) { index ->
                val training = state.trainings[index]
                ListItem(training = training, onClicked = {
                    onAction(
                        TrainingListAction.OpenDetail(
                            training.id,
                            training.source
                        )
                    )
                })
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.trainings.size) { index ->
                val training = state.trainings[index]
                ListItem(training = training, onClicked = {
                    onAction(
                        TrainingListAction.OpenDetail(
                            training.id,
                            training.source
                        )
                    )
                })
            }
        }
    }
}