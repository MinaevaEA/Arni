package com.arni.presentation.ui.screen.request.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.UserHuman
import com.arni.presentation.ui.screen.pickers.time.TimePickerScreen
import com.arni.presentation.ui.screen.pickers.yearmonthday.YearMonthDayPickerScreen
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestAction
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestView
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestViewModel
import com.arni.presentation.ui.screen.select_departament.SelectDepartamentScreen
import com.arni.presentation.ui.screen.select_executor.SelectExecutorScreen
import com.arni.presentation.ui.screen.select_status_patient.SelectStatusPatientScreen
import com.arni.presentation.ui.screen.select_status_request.SelectStatusRequestScreen
import com.arni.presentation.ui.screen.select_subdivision.SelectSubdivisionScreen
import com.arni.presentation.ui.screen.select_urgently_status.SelectUrgentlyStatusScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme

class DetailRequestScreen(val item: RequestHuman, val human: UserHuman): AndroidScreen() {
    @Composable
    override fun Content() {
       DetailRequestScreen(viewModel = koinViewModel{ parametersOf(item, human) })
    }

    @Composable
    private fun DetailRequestScreen(
        viewModel: DetailRequestViewModel
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val state by viewModel.viewStates.collectAsStateWithLifecycle()
        val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

        LaunchedEffect(action) {
            when (val act = action) {
               is DetailRequestAction.returnScreenList -> navigator.pop()
                is DetailRequestAction.OpenTimePicker ->bottomSheetNavigator.show(TimePickerScreen(/* act.id,act.initial)*/))
                is DetailRequestAction.OpenYearMonthDayPicker -> bottomSheetNavigator.show(YearMonthDayPickerScreen())
                is DetailRequestAction.openRequestStatusScreen -> bottomSheetNavigator.show(
                    SelectStatusRequestScreen(act.list))
                is DetailRequestAction.openDepartamentScreen -> bottomSheetNavigator.show(SelectDepartamentScreen(act.listDepartamentHuman))
                is DetailRequestAction.openSubDivisionScreen -> bottomSheetNavigator.show(SelectSubdivisionScreen())
                is DetailRequestAction.openUrgentlyScreen -> bottomSheetNavigator.show(SelectUrgentlyStatusScreen(act.list))
                is DetailRequestAction.openExecutorScreen -> bottomSheetNavigator.show(SelectExecutorScreen(act.list))
                is DetailRequestAction.openStatusPatientScreen -> bottomSheetNavigator.show(SelectStatusPatientScreen(act.list))
                else -> {}
            }
        }

        ArniTheme() {
            DetailRequestView(
                state = state,
                eventConsumer = viewModel::obtainEvent
            )
        }
    }
}
