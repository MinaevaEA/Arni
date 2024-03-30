package com.arni.presentation.ui.screen.select_urgently_status

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.arni.presentation.model.human.UrgencyHuman
import com.arni.presentation.ui.screen.select_urgently_status.ui.SelectUrgentlyStatusAction
import com.arni.presentation.ui.screen.select_urgently_status.ui.SelectUrgentlyStatusView
import com.arni.presentation.ui.screen.select_urgently_status.ui.SelectUrgentlyStatusViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme

class SelectUrgentlyStatusScreen(private val list: List<UrgencyHuman>) : Screen {
    @Composable
    override fun Content() {
        SelectUrgentlyStatusScreen(viewModel = getViewModel{ parametersOf(list) })
    }
}

@Composable
private fun SelectUrgentlyStatusScreen(
    viewModel: SelectUrgentlyStatusViewModel,
) {
    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {
        when (action) {

            SelectUrgentlyStatusAction.OnExist -> bottomSheetNavigator.hide()
            null -> {}
        }
    }

    ArniTheme {
        SelectUrgentlyStatusView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}
