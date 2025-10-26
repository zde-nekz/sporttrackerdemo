package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zdenekskrobak.sporttrackerdemo.R
import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.DurationUnit
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.components.Filter
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.components.TrainingsList
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model.TrainingListAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.model.TrainingListState
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TrainingListScreen(
    onCreateTrainingClicked: () -> Unit,
    onOpenDetail: (String, DataSource) -> Unit
) {
    val viewModel = koinViewModel<TrainingListViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()

    TrainingListScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                is TrainingListAction.CreateTraining -> onCreateTrainingClicked()
                is TrainingListAction.OpenDetail -> onOpenDetail(action.id, action.source)
                else -> viewModel.onAction(action)
            }
        }
    )

}

@Composable
fun TrainingListScreenContent(
    state: TrainingListState,
    onAction: (TrainingListAction) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onFABClicked = {
                onAction(TrainingListAction.CreateTraining)
            })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            Column(
                modifier = Modifier.padding(paddingValues)
            ) {

                Filter(
                    selectedSource = state.filter,
                    onSelected = { source ->
                        onAction(TrainingListAction.Filter(source))
                    })

                if (!state.isLoading) {
                    if (state.trainings.isNotEmpty()) {
                        TrainingsList(
                            state = state,
                            onAction = { action ->
                                onAction(action)
                            })
                    } else {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.no_data),
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun FloatingActionButton(onFABClicked: () -> Unit) {
    FloatingActionButton(onClick = {
        onFABClicked()
    }) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_training)
        )
    }
}

@Preview
@Composable
private fun TrainingListPreview() {
    TrainingListScreenContent(
        state = TrainingListState(
            isLoading = false,
            localTrainings = listOf(
                Training(
                    id = "",
                    name = "Run",
                    place = "Stromovka",
                    duration = 12,
                    durationUnit = DurationUnit.MINUTE,
                    source = DataSource.PHONE
                ),
                Training(
                    id = "",
                    name = "Swimming",
                    place = "Bazen Podoli",
                    duration = 25,
                    durationUnit = DurationUnit.MINUTE,
                    source = DataSource.CLOUD
                )
            )
        ),
        onAction = {}
    )
}