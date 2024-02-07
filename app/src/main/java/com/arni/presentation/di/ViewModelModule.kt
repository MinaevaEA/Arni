package com.arni.presentation.di


import com.arni.presentation.ui.screen.filter.ui.FilterViewModel
import com.arni.presentation.ui.screen.main.MainScreenViewModel
import com.arni.presentation.ui.screen.pickers.time.ui.TimePickerViewModel
import com.arni.presentation.ui.screen.pickers.yearmonth.ui.YearMonthPickerViewModel
import com.arni.presentation.ui.screen.pickers.yearmonthday.ui.YearMonthDayPickerViewModel
import com.arni.presentation.ui.screen.profile.ui.ProfileViewModel
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestViewModel
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
       ProfileViewModel()
    }
    viewModel {
        GeneralRequestViewModel(get())
    }
    viewModel {
        FilterViewModel()
    }
    viewModel {
        CreateRequestViewModel()
    }
    viewModel {
        DetailRequestViewModel(get())
    }
    viewModel { params ->
        TimePickerViewModel(params[0], params[1], params[2], params[3], params[4])
    }

    viewModel { params ->
        YearMonthDayPickerViewModel(params[0], params[1], params[2], params[3])
    }

    viewModel { params ->
        YearMonthPickerViewModel(params[0], params[1], params[2])
    }
}
