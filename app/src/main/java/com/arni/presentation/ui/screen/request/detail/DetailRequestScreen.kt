package com.arni.presentation.ui.screen.request.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.model.human.RequestHuman
import com.arni.presentation.ui.screen.main.MainScreen
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestAction
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestView
import com.arni.presentation.ui.screen.request.detail.ui.DetailRequestViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pro.midev.mec.presentation.ui.style.ArniTheme

class DetailRequestScreen(val item: RequestHuman): AndroidScreen() {
    @Composable
    override fun Content() {
       DetailRequestScreen(viewModel = koinViewModel{ parametersOf(item) })
    }

    @Composable
    private fun DetailRequestScreen(
        viewModel: DetailRequestViewModel
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val state by viewModel.viewStates.collectAsStateWithLifecycle()
        val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

        LaunchedEffect(action) {
            when (val act = action) {
               is DetailRequestAction.returnScreenList -> navigator.pop()
                else -> {}
            }
        }

        ArniTheme() {
            DetailRequestView(
                state = state,
                eventConsumer = viewModel::obtainEvent
            )
        }
    }
}
