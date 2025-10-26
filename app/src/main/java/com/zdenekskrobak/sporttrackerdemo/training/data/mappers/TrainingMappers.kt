package com.zdenekskrobak.sporttrackerdemo.training.data.mappers

import com.zdenekskrobak.sporttrackerdemo.training.data.dto.TrainingEntity
import com.zdenekskrobak.sporttrackerdemo.training.domain.DataSource
import com.zdenekskrobak.sporttrackerdemo.training.domain.DurationUnit
import com.zdenekskrobak.sporttrackerdemo.training.domain.Training
import java.util.UUID

fun TrainingEntity.toDomain(): Training {
    return Training(
        id = id,
        name = name,
        place = place,
        duration = duration,
        durationUnit = DurationUnit.valueOf(durationUnit),
        source = DataSource.PHONE
    )
}

fun Training.toEntity(): TrainingEntity {
    return TrainingEntity(
        id = id.ifBlank { UUID.randomUUID().toString() },
        name = name,
        place = place,
        duration = duration,
        durationUnit = durationUnit.name
    )
}
