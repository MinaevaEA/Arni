package com.arni.domain.di

import com.arni.domain.usecase.auth.AuthUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        AuthUseCase(get(), get())
    }

}
