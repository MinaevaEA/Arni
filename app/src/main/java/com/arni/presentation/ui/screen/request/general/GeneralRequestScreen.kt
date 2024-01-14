package com.arni.presentation.ui.screen.request.general

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestAction
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestEvent
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestView
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestViewModel
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
    viewModel: GeneralRequestViewModel
) {
    val navigator = LocalNavigator.currentOrThrow
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {
        when (val act = action) {
            is GeneralRequestAction.OpenScreenDetailInfo -> {}
            GeneralRequestAction.ExitScreen -> navigator.pop()
            null -> Unit
        }
    }

    LaunchedEffect(null) {
        viewModel.obtainEvent(GeneralRequestEvent.OnCreate)
    }

    ArniTheme() {
        GeneralRequestView(
            state = state,
            eventConsumer = viewModel::obtainEvent
        )
    }
}

