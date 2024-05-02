package com.arni.presentation.ui.screen.select_dispatcher

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import com.arni.presentation.model.human.DispatcherHuman
import com.arni.presentation.ui.screen.select_dispatcher.ui.SelectDispatcherAction
import com.arni.presentation.ui.screen.select_dispatcher.ui.SelectDispatcherViewModel
import com.arni.presentation.ui.screen.select_dispatcher.ui.SelectUrgentlyStatusView
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme

class SelectDispatcherScreen(private val list: List<DispatcherHuman>) : Screen {
    @Composable
    override fun Content() {
        SelectDispatcherScreen(viewModel = getViewModel{ parametersOf(list) })
    }
}

@Composable
private fun SelectDispatcherScreen(
    viewModel: SelectDispatcherViewModel,
) {
    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {
        when (action) {

            SelectDispatcherAction.OnExist -> bottomSheetNavigator.hide()
            null -> {}
        }
    }

    ArniTheme {
        SelectUrgentlyStatusView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}
