package com.zdenekskrobak.sporttrackerdemo.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.zdenekskrobak.sporttrackerdemo.auth.AuthManager
import org.koin.dsl.module

val firebaseModule = module {

    single { FirebaseAuth.getInstance() }

    single {
        FirebaseDatabase.getInstance().apply {
            setPersistenceEnabled(true)
        }
    }

    single { AuthManager(get()) }
}
