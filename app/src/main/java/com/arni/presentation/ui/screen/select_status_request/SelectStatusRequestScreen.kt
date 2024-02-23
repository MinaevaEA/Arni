package com.arni.presentation.ui.screen.select_status_request

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.select_departament.ui.SelectDepartamentView
import com.arni.presentation.ui.screen.select_status_request.ui.SelectRequestStatusView
import com.arni.presentation.ui.screen.select_status_request.ui.SelectStatusRequestAction
import com.arni.presentation.ui.screen.select_status_request.ui.SelectStatusRequestViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class SelectStatusRequestScreen(
/*    private val list: List<RequestStatusHuman>*/
) : Screen {
    @Composable
    override fun Content() {
        SelectStatusRequestScreen(viewModel = koinViewModel()/*getScreenModel { parametersOf(list, index) }, isAuth = isAuth*/)
    }
}

@Composable
private fun SelectStatusRequestScreen(
    viewModel: SelectStatusRequestViewModel,
    isAuth: Boolean = false
) {

    val navigator = LocalNavigator.currentOrThrow
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)


    LaunchedEffect(action) {
        when (action) {

            SelectStatusRequestAction.OnExist -> navigator.pop()

            null -> {}
        }
    }

    ArniTheme {
        SelectRequestStatusView(state = state, eventConsumer = viewModel::obtainEvent)
    }
}
