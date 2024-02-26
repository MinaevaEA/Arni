package com.arni.presentation.ui.screen.select_executor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.select_executor.ui.SelectExecutorAction
import com.arni.presentation.ui.screen.select_executor.ui.SelectExecutorView
import com.arni.presentation.ui.screen.select_executor.ui.SelectExecutorViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class SelectExecutorScreen : Screen {
    @Composable
    override fun Content() {
        SelectExecutorScreen(viewModel = koinViewModel())
    }
}

@Composable
private fun SelectExecutorScreen(
    viewModel: SelectExecutorViewModel,
) {

    val navigator = LocalNavigator.currentOrThrow
    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(action) {
        when (action) {
            SelectExecutorAction.OnExist -> bottomSheetNavigator.hide()
            null -> {}
        }
    }

    ArniTheme {
        SelectExecutorView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}
