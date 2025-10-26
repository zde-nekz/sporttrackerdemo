package com.zdenekskrobak.sporttrackerdemo.di

import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.TrainingDetailViewModel
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.TrainingListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::TrainingListViewModel)
    viewModel { params ->
        TrainingDetailViewModel(
            id = params[0],
            dataSource = params[1],
            roomRepository = get(),
            firebaseRepository = get()
        )
    }
}