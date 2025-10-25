package com.zdenekskrobak.sporttrackerdemo.app

import android.app.Application
import com.zdenekskrobak.sporttrackerdemo.di.initKoin

class SportTrackerApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(this)
    }
}