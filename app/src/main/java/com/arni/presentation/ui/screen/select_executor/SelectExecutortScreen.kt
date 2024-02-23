package com.arni.presentation.ui.screen.select_executor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.select_executor.ui.SelectExecutorAction
import com.arni.presentation.ui.screen.select_executor.ui.SelectExecutorView
import com.arni.presentation.ui.screen.select_executor.ui.SelectExecutorViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class SelectExecutorScreen(
/*    private val list: List<RequestStatusHuman>*/
) : Screen {
    @Composable
    override fun Content() {
        SelectExecutorScreen(viewModel = koinViewModel()/*getScreenModel { parametersOf(list, index) }, isAuth = isAuth*/)
    }
}

@Composable
private fun SelectExecutorScreen(
    viewModel: SelectExecutorViewModel,
    isAuth: Boolean = false
) {

    val navigator = LocalNavigator.currentOrThrow
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(action) {
        when (action) {

            SelectExecutorAction.OnExist -> navigator.pop()

            null -> {}
        }
    }

    ArniTheme {
        SelectExecutorView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}
