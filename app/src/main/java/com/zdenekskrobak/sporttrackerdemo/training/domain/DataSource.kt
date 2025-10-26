package com.zdenekskrobak.sporttrackerdemo.training.domain

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zdenekskrobak.sporttrackerdemo.R

enum class DataSource {
    PHONE,
    CLOUD
}

@Composable
fun DataSource.format(): String {
    return when (this) {
        DataSource.PHONE -> stringResource(R.string.filter_phone)
        DataSource.CLOUD -> stringResource(R.string.filter_cloud)
    }
}