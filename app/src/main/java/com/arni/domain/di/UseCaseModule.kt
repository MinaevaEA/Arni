package com.arni.domain.di

import com.arni.domain.usecase.auth.AuthUseCase
import com.arni.domain.usecase.request.DictionaryUseCase
import com.arni.domain.usecase.request.GetRequestUseCase
import com.arni.domain.usecase.selects.GetExecutorUseCase
import com.arni.domain.usecase.selects.GetPatientStatusUseCase
import com.arni.domain.usecase.selects.GetSelectStatusRequestUseCase
import com.arni.domain.usecase.selects.GetSelectUrgentlyUseCase
import com.arni.domain.usecase.selects.GetSubDivisionUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        AuthUseCase(get(), get())
    }
    factory {
        DictionaryUseCase(get())
    }
    factory {
        GetRequestUseCase(get())
    }
    factory {
        GetSubDivisionUseCase()
    }
    factory {
        GetSelectStatusRequestUseCase()
    }
    factory {
        GetSelectUrgentlyUseCase()
    }
    factory {
        GetExecutorUseCase()
    }
    factory {
        GetPatientStatusUseCase()
    }
}
