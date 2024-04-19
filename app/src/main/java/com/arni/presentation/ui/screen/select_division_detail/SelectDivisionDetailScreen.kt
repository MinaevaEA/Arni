package com.arni.presentation.ui.screen.select_division_detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.arni.presentation.model.human.DivisionHuman
import com.arni.presentation.model.human.DivisionRequestHuman
import com.arni.presentation.ui.screen.select_division_detail.ui.SelectSubDivisionAction
import com.arni.presentation.ui.screen.select_division_detail.ui.SelectSubdivisionView
import com.arni.presentation.ui.screen.select_division_detail.ui.SelectSubdivisionViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme


class SelectDivisionDetailScreen(val list: List<DivisionHuman>) : Screen {
    @Composable
    override fun Content() {
        SelectStatusRequestScreen(viewModel = koinViewModel{ parametersOf(list) })
    }
}

@Composable
private fun SelectStatusRequestScreen(
    viewModel: SelectSubdivisionViewModel,
) {
    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(action) {
        when (action) {
            SelectSubDivisionAction.OnExist -> bottomSheetNavigator.hide()
            null -> {}
        }
    }

    ArniTheme {
        SelectSubdivisionView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}
