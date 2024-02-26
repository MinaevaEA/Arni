package com.arni.presentation.ui.screen.select_status_patient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.select_status_patient.ui.SelectStatusPatientAction
import com.arni.presentation.ui.screen.select_status_patient.ui.SelectStatusPatientView
import com.arni.presentation.ui.screen.select_status_patient.ui.SelectStatusPatientViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class SelectStatusPatientScreen : Screen {
    @Composable
    override fun Content() {
        SelectStatusPatientScreen(viewModel = koinViewModel())
    }
}

@Composable
private fun SelectStatusPatientScreen(
    viewModel: SelectStatusPatientViewModel,
) {

    val navigator = LocalNavigator.currentOrThrow
    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(action) {
        when (action) {

            SelectStatusPatientAction.OnExist -> bottomSheetNavigator.hide()
            null -> {}
        }
    }

    ArniTheme {
        SelectStatusPatientView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}
