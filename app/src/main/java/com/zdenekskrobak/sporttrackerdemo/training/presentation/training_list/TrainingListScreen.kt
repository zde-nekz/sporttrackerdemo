package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.components.Filter
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

    Scaffold { paddingValues ->
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

            }
        }
    }

}

@Preview
@Composable
private fun TrainingListPreview() {
    TrainingListScreenContent(
        state = TrainingListState(
            localTrainings = listOf(
                Training(
                    id = "",
                    name = "Run",
                    place = "Stromovka",
                    length = "12m",
                    source = DataSource.DATABASE
                ),
                Training(
                    id = "",
                    name = "Swimming",
                    place = "Bazen Podoli",
                    length = "25m",
                    source = DataSource.DATABASE
                )
            )
        ),
        onAction = {}
    )
}