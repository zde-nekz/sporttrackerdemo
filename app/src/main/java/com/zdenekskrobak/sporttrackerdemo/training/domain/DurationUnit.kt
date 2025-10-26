package com.zdenekskrobak.sporttrackerdemo.training.domain

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zdenekskrobak.sporttrackerdemo.R

enum class DurationUnit {
    SECOND,
    MINUTE,
    HOUR
}

@Composable
fun DurationUnit.format(): String {
    return when (this) {
        DurationUnit.SECOND -> stringResource(R.string.time_unit_s)
        DurationUnit.MINUTE -> stringResource(R.string.time_unit_m)
        DurationUnit.HOUR -> stringResource(R.string.time_unit_h)
    }
}