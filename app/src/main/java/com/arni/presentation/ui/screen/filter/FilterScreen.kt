package com.arni.presentation.ui.screen.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.filter.ui.FilterView
import com.arni.presentation.ui.screen.filter.ui.FilterViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class FilterScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        FilterScreen(viewModel = koinViewModel())
    }
}

@Composable
private fun FilterScreen(
    viewModel: FilterViewModel
) {
    val navigator = LocalNavigator.currentOrThrow
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {

        when(action) {
            null -> Unit
            else -> {}
        }
    }


    ArniTheme() {
        FilterView(
            state = state,
            eventConsumer = viewModel::obtainEvent
        )
    }
}
