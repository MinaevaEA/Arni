package com.arni.presentation.ui.screen.request.create

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.ui.screen.pickers.time.TimePickerScreen
import com.arni.presentation.ui.screen.pickers.yearmonthday.YearMonthDayPickerScreen
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestAction
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestEvent
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestView
import com.arni.presentation.ui.screen.request.create.ui.CreateRequestViewModel
import com.arni.presentation.ui.screen.select_departament.SelectDepartmentScreen
import com.arni.presentation.ui.screen.select_dispatcher.SelectDispatcherScreen
import com.arni.presentation.ui.screen.select_division_detail.SelectDivisionDetailScreen
import com.arni.presentation.ui.screen.select_executor.SelectExecutorScreen
import com.arni.presentation.ui.screen.select_status_patient.SelectStatusPatientScreen
import com.arni.presentation.ui.screen.select_status_request.SelectStatusRequestScreen
import com.arni.presentation.ui.screen.select_urgently_status.SelectUrgentlyStatusScreen
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme

class CreateRequestScreen(
    val listId: String,
    val dictionaryHuman: DictionaryHuman,
    val divisionHuman: DivisionHuman
): Screen {
    @Composable
    override fun Content() {
       CreateRequestScreen(viewModel = koinViewModel { parametersOf(listId, dictionaryHuman, divisionHuman) })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("NotConstructor")
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
                is CreateRequestAction.OpenTimePickerRequest -> {
                    bottomSheetNavigator.show(TimePickerScreen(act.initial, act.minDate,act.maxDate, act.id))
                }
                CreateRequestAction.returnGeneralScreen -> navigator.pop()
                is CreateRequestAction.OpenYearMonthDayPickerRequest -> {
                    bottomSheetNavigator.show(YearMonthDayPickerScreen(act.selectDate, act.maxDate, act.minDate, act.id))}
                is CreateRequestAction.openRequestStatusScreen -> bottomSheetNavigator.show(
                    SelectStatusRequestScreen(act.list)
                )
                is CreateRequestAction.openDepartamentScreenFrom -> bottomSheetNavigator.show(
                    SelectDepartmentScreen(departments = act.listDepartmentHuman, onSelect = {
                        viewModel.obtainEvent(CreateRequestEvent.OnDepartmentFrom(it))
                    })
                )
                is CreateRequestAction.openDispatcherScreen -> bottomSheetNavigator.show(SelectDispatcherScreen(act.list))
                is CreateRequestAction.openUrgentlyScreen -> bottomSheetNavigator.show(SelectUrgentlyStatusScreen(act.list))
                is CreateRequestAction.openDepartamentScreenTo -> bottomSheetNavigator.show(
                    SelectDepartmentScreen(departments = act.listDepartmentHuman, onSelect = {
                        viewModel.obtainEvent(CreateRequestEvent.OnDepartmentTo(it))
                    })
                )
                is CreateRequestAction.OpenTimePickerStart -> {
                    bottomSheetNavigator.show(TimePickerScreen(act.initial, act.minDate,act.maxDate, act.id))
                }
                is CreateRequestAction.OpenTimePickerEnd -> {
                    bottomSheetNavigator.show(TimePickerScreen(act.initial, act.minDate,act.maxDate, act.id))
                }
                is CreateRequestAction.OpenYearMonthDayPickerStart -> {
                    bottomSheetNavigator.show(YearMonthDayPickerScreen(act.selectDate, act.maxDate, act.minDate, act.id))
                }
                is CreateRequestAction.OpenYearMonthDayPickerEnd -> {
                    bottomSheetNavigator.show(YearMonthDayPickerScreen(act.selectDate, act.maxDate, act.minDate, act.id))
                }
                is CreateRequestAction.openDivisionScreen -> bottomSheetNavigator.show(SelectDivisionDetailScreen(act.listDivision))
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
