package com.zdenekskrobak.sporttrackerdemo.di

import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_detail.TrainingDetailViewModel
import com.zdenekskrobak.sporttrackerdemo.training.presentation.training_list.TrainingListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::TrainingListViewModel)
    viewModelOf(::TrainingDetailViewModel)
}