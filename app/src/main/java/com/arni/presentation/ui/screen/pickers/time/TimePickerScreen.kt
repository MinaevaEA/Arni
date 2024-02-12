package com.arni.presentation.ui.screen.pickers.time

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.arni.presentation.ui.screen.pickers.time.ui.TimePickerAction
import com.arni.presentation.ui.screen.pickers.time.ui.TimePickerEvent
import com.arni.presentation.ui.screen.pickers.time.ui.TimePickerView
import com.arni.presentation.ui.screen.pickers.time.ui.TimePickerViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme
import java.time.LocalTime

class TimePickerScreen(
 /*   private val id: Int,
    private val initial: LocalTime = LocalTime.now(),*/
) : AndroidScreen() {

    @Composable
    override fun Content() {
        TimePickerScreen(viewModel = koinViewModel() /*{ parametersOf(id, initial) }*/ )
    }
}

@Composable
private fun TimePickerScreen(
    viewModel: TimePickerViewModel
) {

    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(true) {
        viewModel.obtainEvent(TimePickerEvent.OnCreate)
    }

    LaunchedEffect(action) {
        when (action) {
            TimePickerAction.Dismiss -> bottomSheetNavigator.hide()
            null -> Unit
        }
    }

    ArniTheme {
        TimePickerView(
            state = state,
            eventConsumer = viewModel::obtainEvent
        )
    }
}
