package com.arni.presentation.di


import com.arni.presentation.ui.screen.main.MainScreenViewModel
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestViewModel
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestViewModel
import com.arni.presentation.ui.screen.signIn.ui.SignInViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel {
        MainScreenViewModel()
    }

    viewModel {
        SignInViewModel(get())
    }
    viewModel {
        GeneralRequestViewModel(get())
    }
    viewModel {
        DetailRequestViewModel()
    }
}
