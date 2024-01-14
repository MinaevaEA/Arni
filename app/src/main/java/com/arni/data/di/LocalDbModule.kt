package com.arni.data.di

import com.arni.data.local.AppDatabase
import org.koin.dsl.module

val localDbModule = module {
    single {
        AppDatabase.create(get())
    }
}
