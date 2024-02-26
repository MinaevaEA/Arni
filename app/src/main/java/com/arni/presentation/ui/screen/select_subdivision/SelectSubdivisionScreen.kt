package com.arni.presentation.ui.screen.select_subdivision

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.select_subdivision.ui.SelectSubdivisionAction
import com.arni.presentation.ui.screen.select_subdivision.ui.SelectSubdivisionView
import com.arni.presentation.ui.screen.select_subdivision.ui.SelectSubdivisionViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme


class SelectSubdivisionScreen() : Screen {
    @Composable
    override fun Content() {
        SelectStatusRequestScreen(viewModel = koinViewModel())
    }
}

@Composable
private fun SelectStatusRequestScreen(
    viewModel: SelectSubdivisionViewModel,
) {

    val navigator = LocalNavigator.currentOrThrow
    val bottomSheetNavigator = LocalBottomSheetNavigator.current
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(action) {
        when (action) {

            SelectSubdivisionAction.OnExist -> navigator.pop()
            null -> {}
        }
    }

    ArniTheme {
        SelectSubdivisionView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}
