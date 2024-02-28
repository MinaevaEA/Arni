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
import com.arni.presentation.ui.screen.select_status_request.ui.SelectStatusRequestViewModel
import com.arni.presentation.ui.screen.select_departament.ui.SelectDepartamentViewModel
import com.arni.presentation.ui.screen.select_executor.ui.SelectExecutorViewModel
import com.arni.presentation.ui.screen.select_status_patient.ui.SelectStatusPatientViewModel
import com.arni.presentation.ui.screen.select_subdivision.ui.SelectSubdivisionViewModel
import com.arni.presentation.ui.screen.select_urgently_status.ui.SelectUrgentlyStatusViewModel
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
        CreateRequestViewModel(get(), get(), get(), get())
    }
    viewModel {
        SelectExecutorViewModel(get())
    }
    viewModel {
        SelectStatusPatientViewModel(get())
    }

    viewModel { params ->
        SelectDepartamentViewModel(params[0])
    }
    viewModel {
        SelectUrgentlyStatusViewModel(get())
    }

    viewModel { params ->
        SelectSubdivisionViewModel(get())
    }
    viewModel { params ->
        SelectStatusRequestViewModel(params[0])
    }
    viewModel {
        DetailRequestViewModel(get(), get(),get(),get(),get())
    }
    viewModel {
        TimePickerViewModel(/*params[0], params[1], params[2], params[3], params[4]*/)
    }

    viewModel {
        YearMonthDayPickerViewModel(/*params[0], params[1], params[2], params[3]*/)
    }

    viewModel { params ->
        YearMonthPickerViewModel(params[0], params[1], params[2])
    }
}
