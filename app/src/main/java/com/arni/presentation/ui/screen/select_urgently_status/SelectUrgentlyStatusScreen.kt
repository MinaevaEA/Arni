package com.arni.presentation.ui.screen.select_urgently_status

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.select_urgently_status.ui.SelectUrgentlyStatusAction
import com.arni.presentation.ui.screen.select_urgently_status.ui.SelectUrgentlyStatusView
import com.arni.presentation.ui.screen.select_urgently_status.ui.SelectUrgentlyStatusViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class SelectUrgentlyStatusScreen(
/*    private val list: List<RequestStatusHuman>*/
) : Screen {
    @Composable
    override fun Content() {
        SelectUrgentlyStatusScreen(viewModel = koinViewModel()/*getScreenModel { parametersOf(list, index) }, isAuth = isAuth*/)
    }
}

@Composable
private fun SelectUrgentlyStatusScreen(
    viewModel: SelectUrgentlyStatusViewModel,
    isAuth: Boolean = false
) {

    val navigator = LocalNavigator.currentOrThrow
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(action) {
        when (action) {

            SelectUrgentlyStatusAction.OnExist -> navigator.pop()

            null -> {}
        }
    }

    ArniTheme {
        SelectUrgentlyStatusView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}