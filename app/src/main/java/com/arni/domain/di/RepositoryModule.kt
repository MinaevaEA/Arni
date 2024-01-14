package com.arni.domain.di

import com.arni.domain.repository.user.UserRepositoryRemote
import org.koin.dsl.module

val repositoryModule = module {

    single {
        UserRepositoryRemote(get())
    }
}
