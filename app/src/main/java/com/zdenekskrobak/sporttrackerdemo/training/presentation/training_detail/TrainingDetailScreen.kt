@file:OptIn(ExperimentalMaterial3Api::class)

package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zdenekskrobak.sporttrackerdemo.R
import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components.DeleteButton
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components.DurationField
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components.NameField
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components.PlaceField
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components.SaveButton
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components.SourceDropdown
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailState
import com.zdenekskrobak.sporttrackerdemo.ui.theme.Purple40
import com.zdenekskrobak.sporttrackerdemo.ui.theme.White
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TrainingDetailScreen(
    id: String?,
    dataSource: DataSource?,
    onNavigateBack: () -> Unit
) {
    val viewModel: TrainingDetailViewModel = koinViewModel(
        parameters = { parametersOf(id, dataSource) }
    )

    LaunchedEffect(Unit) {
        viewModel.goBackEvent.collect {
            onNavigateBack()
        }
    }

    val state by viewModel.state.collectAsState()

    TrainingDetailScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                TrainingDetailAction.OnBackButtonClicked -> {
                    onNavigateBack()
                }

                else -> viewModel.onAction(action)
            }
        })
}

@Composable
fun TrainingDetailScreenContent(
    modifier: Modifier = Modifier,
    state: TrainingDetailState,
    onAction: (TrainingDetailAction) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(R.string.edit_training.takeIf { state.id != null }
                        ?: R.string.add_training))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        onAction(TrainingDetailAction.OnBackButtonClicked)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40,
                    titleContentColor = White,
                    navigationIconContentColor = White
                )
            )
        },
        content = { padding ->
            Column(
                modifier = modifier
                    .padding(padding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NameField(
                    state = state,
                    onNameChanged = { name ->
                        onAction(TrainingDetailAction.OnNameChanged(name))
                    })
                PlaceField(
                    state = state,
                    onPlaceChanged = { place ->
                        onAction(TrainingDetailAction.OnPlaceChanged(place))
                    })
                DurationField(state, onAction)
                SourceDropdown(state, onSourceChanged = { source ->
                    onAction(TrainingDetailAction.OnSourceChanged(source))
                })
                SaveButton(onSave = {
                    onAction(TrainingDetailAction.OnSaveClicked)
                })

                if (state.id != null) {
                    DeleteButton(
                        onDelete = {
                            onAction(TrainingDetailAction.OnDeleteClicked)
                        }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun TrainingDetailScreenPreview() {
    TrainingDetailScreenContent(
        state = TrainingDetailState(),
        onAction = {}
    )

}
