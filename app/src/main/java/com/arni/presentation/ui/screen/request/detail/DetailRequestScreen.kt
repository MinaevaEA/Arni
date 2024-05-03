package com.arni.presentation.ui.screen.request.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.model.human.DictionaryHuman
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.model.human.UserHuman
import com.arni.presentation.ui.screen.pickers.time.TimePickerScreen
import com.arni.presentation.ui.screen.pickers.yearmonthday.YearMonthDayPickerScreen
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestAction
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestEvent
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestView
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestViewModel
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

class DetailRequestScreen(
    val listId: String,
    val item: RequestHuman,
    val human: UserHuman,
    val dictionaryHuman: DictionaryHuman,
    val divisionHuman: DivisionHuman
) : AndroidScreen() {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        DrawDetailRequestScreen(viewModel = koinViewModel { parametersOf(listId,item, human, dictionaryHuman, divisionHuman) })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    private fun DrawDetailRequestScreen(
        viewModel: DetailRequestViewModel
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val bottomSheetNavigator = LocalBottomSheetNavigator.current
        val state by viewModel.viewStates.collectAsStateWithLifecycle()
        val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

        LaunchedEffect(action) {
            when (val act = action) {
                is DetailRequestAction.returnScreenList -> navigator.pop()
                is DetailRequestAction.OpenTimePickerRequest -> {
                    bottomSheetNavigator.show(TimePickerScreen(act.initial, act.minDate,act.maxDate, act.id))
                }
                is DetailRequestAction.OpenTimePickerStart -> {
                    bottomSheetNavigator.show(TimePickerScreen(act.initial, act.minDate,act.maxDate, act.id))
                }
                is DetailRequestAction.OpenTimePickerEnd -> {
                    bottomSheetNavigator.show(TimePickerScreen(act.initial, act.minDate,act.maxDate, act.id))
                }
                is DetailRequestAction.OpenYearMonthDayPickerRequest -> {
                    bottomSheetNavigator.show(YearMonthDayPickerScreen(act.selectDate, act.maxDate, act.minDate, act.id))
                }
                is DetailRequestAction.OpenYearMonthDayPickerStart -> {
                    bottomSheetNavigator.show(YearMonthDayPickerScreen(act.selectDate, act.maxDate, act.minDate, act.id))
                }
                is DetailRequestAction.OpenYearMonthDayPickerEnd -> {
                    bottomSheetNavigator.show(YearMonthDayPickerScreen(act.selectDate, act.maxDate, act.minDate, act.id))
                }
                is DetailRequestAction.openRequestStatusScreen -> bottomSheetNavigator.show(
                    SelectStatusRequestScreen(act.list)
                )

                is DetailRequestAction.openDepartamentScreenFrom -> bottomSheetNavigator.show(
                    SelectDepartmentScreen(departments = act.listDepartmentHuman, onSelect = {
                        viewModel.obtainEvent(DetailRequestEvent.OnDepartmentFrom(it))
                    })
                )

                is DetailRequestAction.openDepartamentScreenTo -> bottomSheetNavigator.show(
                    SelectDepartmentScreen(departments = act.listDepartmentHuman, onSelect = {
                        viewModel.obtainEvent(DetailRequestEvent.OnDepartmentTo(it))
                    })
                )

                is DetailRequestAction.openDivisionScreen -> bottomSheetNavigator.show(SelectDivisionDetailScreen(act.listSubDivision))
                is DetailRequestAction.openUrgentlyScreen -> bottomSheetNavigator.show(SelectUrgentlyStatusScreen(act.list))
                is DetailRequestAction.openDispatcherScreen -> bottomSheetNavigator.show(SelectDispatcherScreen(act.list))
                is DetailRequestAction.openExecutorScreen -> bottomSheetNavigator.show(SelectExecutorScreen(act.list))
                is DetailRequestAction.openStatusPatientScreen -> bottomSheetNavigator.show(
                    SelectStatusPatientScreen(
                        act.list
                    )
                )

                else -> {}
            }
        }

        ArniTheme {
            DetailRequestView(
                state = state,
                eventConsumer = viewModel::obtainEvent
            )
        }
    }
}
