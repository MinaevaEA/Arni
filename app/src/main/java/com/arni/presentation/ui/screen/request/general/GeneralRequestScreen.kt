package com.arni.presentation.ui.screen.request.general

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.request.create.CreateRequestScreen
import com.arni.presentation.ui.screen.request.detail.DetailRequestScreen
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestAction
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestEvent
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestView
import com.arni.presentation.ui.screen.request.general.ui.GeneralRequestViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class GeneralRequestScreen : AndroidScreen() {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {
        GeneralCheckingScreen(viewModel = koinViewModel())
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun GeneralCheckingScreen(
    viewModel: GeneralRequestViewModel
) {
    val navigator = LocalNavigator.currentOrThrow
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {
        when (val act = action) {
            is GeneralRequestAction.OpenScreenDetailInfo -> navigator.push(DetailRequestScreen(act.item))
            is GeneralRequestAction.OpenScreenAddRequest -> navigator.push(CreateRequestScreen())
            GeneralRequestAction.ExitScreen -> navigator.pop()
            null -> Unit
        }
    }

    ArniTheme() {
        GeneralRequestView(
            state = state,
            eventConsumer = viewModel::obtainEvent
        )
    }
}

