package com.zdenekskrobak.sporttrackerdemo.di

import com.zdenekskrobak.sporttrackerdemo.training.data.repository.TrainingRepositoryImpl
import com.zdenekskrobak.sporttrackerdemo.training.domain.TrainingRepository
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.TrainingDetailViewModel
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.TrainingListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<TrainingRepository> {
        TrainingRepositoryImpl(trainingDao = get())
    }
    viewModelOf(::TrainingListViewModel)
    viewModel { params ->
        TrainingDetailViewModel(
            id = params[0],
            dataSource = params[1],
            repository = get(),
        )
    }
}