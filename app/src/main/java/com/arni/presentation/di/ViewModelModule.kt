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
import com.arni.presentation.ui.screen.request.select_division_general.ui.SelectDivisionViewModel
import com.arni.presentation.ui.screen.select_departament.ui.SelectDepartmentViewModel
import com.arni.presentation.ui.screen.select_executor.ui.SelectExecutorViewModel
import com.arni.presentation.ui.screen.select_status_patient.ui.SelectStatusPatientViewModel
import com.arni.presentation.ui.screen.select_status_request.ui.SelectStatusRequestViewModel
import com.arni.presentation.ui.screen.select_division_detail.ui.SelectSubdivisionViewModel
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
        GeneralRequestViewModel(get(), get(), get())
    }
    viewModel {
        SelectDivisionViewModel(get(), get())
    }
    viewModel {
        FilterViewModel()
    }
    viewModel {
        CreateRequestViewModel(get(), get(), get())
    }
    viewModel {
        SelectExecutorViewModel(get())
    }
    viewModel {
        SelectStatusPatientViewModel(get())
    }
    viewModel {
        SelectUrgentlyStatusViewModel(get())
    }

    viewModel { params ->
        SelectSubdivisionViewModel(get(), get())
    }
    viewModel { params ->
        SelectStatusRequestViewModel(params[0])
    }
    viewModel {
        DetailRequestViewModel(get(), get(), get(), get(), get(), get())
    }
    viewModel { params ->
        TimePickerViewModel(params[0], params[1], params[2])
    }

    viewModel { params ->
        YearMonthDayPickerViewModel(params[0], params[1], params[2])
    }

    viewModel { params ->
        YearMonthPickerViewModel(params[0], params[1], params[2])
    }
}
