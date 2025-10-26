package com.zdenekskrobak.sporttrackerdemo.di

import com.zdenekskrobak.sporttrackerdemo.training.data.repository.FirebaseTrainingRepository
import com.zdenekskrobak.sporttrackerdemo.training.data.repository.RoomTrainingRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        RoomTrainingRepository(trainingDao = get())
    }
    single {
        FirebaseTrainingRepository(authManager = get(), database = get())
    }
}