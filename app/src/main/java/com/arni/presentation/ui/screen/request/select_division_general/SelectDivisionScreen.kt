package com.arni.presentation.ui.screen.request.select_division_general

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.ui.screen.request.select_division_general.ui.SelectDivisionAction
import com.arni.presentation.ui.screen.request.select_division_general.ui.SelectDivisionView
import com.arni.presentation.ui.screen.request.select_division_general.ui.SelectDivisionViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme


class SelectDivisionScreen(val list: List<DivisionHuman>, val listID: String) : Screen {
    @Composable
    override fun Content() {
        SelectStatusRequestScreen(viewModel = koinViewModel { parametersOf(list, listID) })
    }
}

@Composable
private fun SelectStatusRequestScreen(
    viewModel: SelectDivisionViewModel,
) {

    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(action) {
        when (action) {
            SelectDivisionAction.OnExist -> bottomSheetNavigator.hide()
            else -> {}
        }
    }

    ArniTheme {
        SelectDivisionView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}
