package com.zdenekskrobak.sporttrackerdemo.app

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object TrainingList: Route

    @Serializable
    data class TrainingDetail(val id: String?): Route
}