package com.arni.presentation.ui.screen.general

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.general.ui.GeneralCheckingAction
import com.arni.presentation.ui.screen.general.ui.GeneralCheckingEvent
import com.arni.presentation.ui.screen.general.ui.GeneralCheckingView
import com.arni.presentation.ui.screen.general.ui.GeneralCheckingViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class GeneralRequestScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        GeneralCheckingScreen(viewModel = koinViewModel())
    }
}

@Composable
private fun GeneralCheckingScreen(
    viewModel: GeneralCheckingViewModel
) {
    val navigator = LocalNavigator.currentOrThrow
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {
        when (val act = action) {
            is GeneralCheckingAction.OpenScreenDetailInfo -> {}
            GeneralCheckingAction.ExitScreen -> navigator.pop()
            null -> Unit
        }
    }

    LaunchedEffect(null) {
        viewModel.obtainEvent(GeneralCheckingEvent.OnCreate)
    }

    ArniTheme() {
        GeneralCheckingView(
            state = state,
            eventConsumer = viewModel::obtainEvent
        )
    }
}

