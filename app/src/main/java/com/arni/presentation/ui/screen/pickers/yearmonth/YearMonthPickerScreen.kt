package com.arni.presentation.ui.screen.pickers.yearmonth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.arni.presentation.ui.screen.pickers.yearmonth.ui.YearMonthPickerAction
import com.arni.presentation.ui.screen.pickers.yearmonth.ui.YearMonthPickerEvent
import com.arni.presentation.ui.screen.pickers.yearmonth.ui.YearMonthPickerView
import com.arni.presentation.ui.screen.pickers.yearmonth.ui.YearMonthPickerViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme
import java.time.YearMonth

class YearMonthPickerScreen(
    private val initial: YearMonth? = null,
    private val min: YearMonth? = YearMonth.now().minusMonths(11),
    private val max: YearMonth? = YearMonth.now().plusMonths(11)
) : AndroidScreen() {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        YearMonthPickerScreen(
            viewModel = getScreenModel { parametersOf(initial, min, max) }
        )
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun YearMonthPickerScreen(
    viewModel: YearMonthPickerViewModel
) {

    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {
        when (action) {
            YearMonthPickerAction.Dismiss -> bottomSheetNavigator.hide()
            null -> Unit
        }
    }

    LaunchedEffect(true) {
        viewModel.obtainEvent(YearMonthPickerEvent.OnCreate)
    }

    ArniTheme {
        YearMonthPickerView(
            state = state,
            eventConsumer = viewModel::obtainEvent
        )
    }
}
