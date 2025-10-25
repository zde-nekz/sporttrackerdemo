@file:OptIn(ExperimentalMaterial3Api::class)

package com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zdenekskrobak.sporttrackerdemo.R
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components.LengthField
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components.SaveButton
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.components.SourceDropdown
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailAction
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.model.TrainingDetailState
import com.zdenekskrobak.sporttrackerdemo.ui.theme.Purple40
import com.zdenekskrobak.sporttrackerdemo.ui.theme.White
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrainingDetailScreen(
    id: String?,
    onNavigateBack: () -> Unit
) {
    val viewModel: TrainingDetailViewModel = koinViewModel()

    val state by viewModel.state.collectAsState()

    TrainingDetailScreenContent(
        state = state,
        onAction = { action ->
            when (action) {
                TrainingDetailAction.OnSaveClicked -> {
                    TODO()
                }

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
                LengthField(state, onAction)
                SourceDropdown(state, onAction)
                SaveButton(onSave = {
                    onAction(TrainingDetailAction.OnSaveClicked)
                })
            }
        }
    )
}

@Composable
private fun NameField(
    state: TrainingDetailState,
    onNameChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.name,
        onValueChange = { onNameChanged(it) },
        label = { Text(stringResource(R.string.name)) },

        )
}

@Composable
private fun PlaceField(
    state: TrainingDetailState,
    onPlaceChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = state.place,
        onValueChange = { onPlaceChanged(it) },
        label = { Text(stringResource(R.string.place)) },
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
