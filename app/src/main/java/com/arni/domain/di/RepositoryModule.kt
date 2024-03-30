package com.arni.domain.di

import com.arni.domain.repository.DictionaryRepositoryRemote
import com.arni.domain.repository.RequestRepositoryRemote
import com.arni.domain.repository.user.AuthRepositoryRemote
import org.koin.dsl.module

val repositoryModule = module {

    single {
        AuthRepositoryRemote(get())
    }
    single {
        DictionaryRepositoryRemote(get())
    }
    single {
        RequestRepositoryRemote(get())
    }
}
