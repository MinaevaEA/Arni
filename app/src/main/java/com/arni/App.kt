package com.arni

import android.app.Application
import com.arni.data.di.keyStorageModule
import com.arni.data.di.localDbModule
import com.arni.data.di.remoteStorageModule
import com.arni.domain.di.repositoryModule
import com.arni.domain.di.useCaseModule
import com.arni.presentation.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App.baseContext)
            modules(
                viewModelModule,
                keyStorageModule,
                localDbModule,
                remoteStorageModule,
                repositoryModule,
                useCaseModule
            )
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}

