package com.arni.presentation.ui.screen.request.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.pickers.time.TimePickerScreen
import com.arni.presentation.ui.screen.pickers.yearmonthday.YearMonthDayPickerScreen
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestAction
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestView
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestViewModel
import com.arni.presentation.ui.screen.select_departament.SelectDepartamentScreen
import com.arni.presentation.ui.screen.select_executor.SelectExecutorScreen
import com.arni.presentation.ui.screen.select_status_patient.SelectStatusPatientScreen
import com.arni.presentation.ui.screen.select_status_request.SelectStatusRequestScreen
import com.arni.presentation.ui.screen.select_subdivision.SelectSubdivisionScreen
import com.arni.presentation.ui.screen.select_urgently_status.SelectUrgentlyStatusScreen
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class CreateRequestScreen: AndroidScreen() {
    @Composable
    override fun Content() {
       CreateRequestScreen(viewModel = koinViewModel())
    }

    @Composable
    private fun CreateRequestScreen(
        viewModel: CreateRequestViewModel
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val state by viewModel.viewStates.collectAsStateWithLifecycle()
        val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

        LaunchedEffect(action) {
            when (val act = action) {
                CreateRequestAction.returnGeneralScreen -> navigator.pop()
                is CreateRequestAction.OpenTimePicker ->bottomSheetNavigator.show(TimePickerScreen(/* act.id,act.initial)*/))
                is CreateRequestAction.OpenYearMonthDayPicker -> bottomSheetNavigator.show(YearMonthDayPickerScreen())
                is CreateRequestAction.openRequestStatusScreen -> bottomSheetNavigator.show(
                    SelectStatusRequestScreen(act.list)
                )
                is CreateRequestAction.openDepartamentScreen -> bottomSheetNavigator.show(SelectDepartamentScreen(act.listDepartamentHuman))
                is CreateRequestAction.openSubDivisionScreen -> bottomSheetNavigator.show(SelectSubdivisionScreen())
                is CreateRequestAction.openUrgentlyScreen -> bottomSheetNavigator.show(SelectUrgentlyStatusScreen(act.list))
                is CreateRequestAction.openExecutorScreen -> bottomSheetNavigator.show(SelectExecutorScreen(act.list))
                is CreateRequestAction.openStatusPatientScreen -> bottomSheetNavigator.show(SelectStatusPatientScreen(act.list))
                else -> {}
            }
        }

        ArniTheme() {
            CreateRequestView(
                state = state,
                eventConsumer = viewModel::obtainEvent
            )
        }
    }
}
