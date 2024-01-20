package com.arni.presentation.ui.screen.signIn

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ext.LocalGlobalNavigator
import com.arni.presentation.ui.screen.main.MainScreen
import com.arni.presentation.ui.screen.request.general.GeneralRequestScreen
import com.arni.presentation.ui.screen.signIn.ui.SignInAction
import com.arni.presentation.ui.screen.signIn.ui.SignInView
import com.arni.presentation.ui.screen.signIn.ui.SignInViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme


class SignInScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        SignInScreen(viewModel = koinViewModel())
    }
}

@Composable
private fun SignInScreen(
    viewModel: SignInViewModel
) {
    val navigator = LocalNavigator.currentOrThrow
    val state by viewModel.viewStates.collectAsStateWithLifecycle()
    val action by viewModel.viewActions.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(action) {

        when(action) {
            SignInAction.OpenNextScreen -> navigator.replaceAll(MainScreen())
            SignInAction.OpenRestorePasswordScreen -> navigator.replaceAll(MainScreen())
            null -> Unit
        }
    }


    ArniTheme() {
        SignInView(
            state = state,
            eventConsumer = viewModel::obtainEvent
        )
    }
}
