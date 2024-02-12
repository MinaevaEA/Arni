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
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme
import java.time.LocalDate
import java.time.LocalTime

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
