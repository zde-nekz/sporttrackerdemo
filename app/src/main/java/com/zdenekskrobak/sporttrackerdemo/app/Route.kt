package com.zdenekskrobak.sporttrackerdemo.app

import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    @Serializable
    data object TrainingList: Route

    @Serializable
    data class TrainingDetail(val id: String?, val source: DataSource?): Route
}