package com.arni.presentation.ui.screen.select_status_patient

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.arni.presentation.model.human.StatusPatientHuman
import com.arni.presentation.ui.screen.select_status_patient.ui.SelectStatusPatientAction
import com.arni.presentation.ui.screen.select_status_patient.ui.SelectStatusPatientView
import com.arni.presentation.ui.screen.select_status_patient.ui.SelectStatusPatientViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme

class SelectStatusPatientScreen(private val list: List<StatusPatientHuman>) : Screen {
    @Composable
    override fun Content() {
        SelectStatusPatientScreen(viewModel = getViewModel { parametersOf(list) })
    }
}

@Composable
private fun SelectStatusPatientScreen(
    viewModel: SelectStatusPatientViewModel,
) {
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
