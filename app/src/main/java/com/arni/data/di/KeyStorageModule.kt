package com.arni.data.di

import com.arni.data.local.keystorage.UserKeyStorage
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val keyStorageModule = module {

    single {
        MMKV.initialize(androidContext())
        MMKV.defaultMMKV()
    }

    single {
        UserKeyStorage(get())
    }
}
