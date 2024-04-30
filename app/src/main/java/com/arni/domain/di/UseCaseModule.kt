package com.arni.domain.di

import com.arni.domain.usecase.GetRequestChangeDetailUseCase
import com.arni.domain.usecase.GetRequestEditUseCase
import com.arni.domain.usecase.auth.AuthUseCase
import com.arni.domain.usecase.request.GetChangeRequestUseCase
import com.arni.domain.usecase.request.GetDictionaryUseCase
import com.arni.domain.usecase.request.GetRequestUseCase
import com.arni.domain.usecase.selects_delete.GetExecutorUseCase
import com.arni.domain.usecase.selects_delete.GetPatientStatusUseCase
import com.arni.domain.usecase.selects_delete.GetSelectStatusRequestUseCase
import com.arni.domain.usecase.selects_delete.GetSelectUrgentlyUseCase
import com.arni.domain.usecase.selects_delete.GetSubDivisionUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory {
        AuthUseCase(get(), get())
    }
    factory {
        GetDictionaryUseCase(get())
    }
    factory {
        GetRequestChangeDetailUseCase(get())
    }

    factory {
        GetRequestEditUseCase(get())
    }
    factory {
        GetRequestUseCase(get())
    }
    factory {
        GetChangeRequestUseCase(get())
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
