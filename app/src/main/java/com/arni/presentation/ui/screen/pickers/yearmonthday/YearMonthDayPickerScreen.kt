package com.arni.presentation.ui.screen.pickers.yearmonthday

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.arni.presentation.model.human.DepartmentHuman
import com.arni.presentation.ui.screen.pickers.yearmonthday.ui.YearMonthDayPickerAction
import com.arni.presentation.ui.screen.pickers.yearmonthday.ui.YearMonthDayPickerState
import com.arni.presentation.ui.screen.pickers.yearmonthday.ui.YearMonthDayPickerView
import com.arni.presentation.ui.screen.pickers.yearmonthday.ui.YearMonthDayPickerViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme
import java.time.LocalDate

class YearMonthDayPickerScreen(
    private val selectDate: LocalDate? = null,
    private val minDate: LocalDate? = null,
    private val maxDate: LocalDate? = null,
    private val idDate: Int = 0,
) : Screen {
    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        YearMonthDayPickerScreen(
            viewModel = koinViewModel { parametersOf(selectDate, minDate, maxDate)  }, idDate = idDate
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun YearMonthDayPickerScreen(
    viewModel: YearMonthDayPickerViewModel,
    idDate: Int = 0,
) {

    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {
    when (action) {
        YearMonthDayPickerAction.Dismiss -> bottomSheetNavigator.hide()
        null -> Unit
    }
    }

    ArniTheme {
        YearMonthDayPickerView(
            state = state,
            eventConsumer = viewModel::obtainEvent,
            idDate = idDate
        )
    }
}
