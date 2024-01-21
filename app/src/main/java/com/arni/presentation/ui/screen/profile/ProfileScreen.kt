package com.arni.presentation.ui.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arni.presentation.ui.screen.profile.ui.ProfileView
import com.arni.presentation.ui.screen.profile.ui.ProfileViewModel
import org.koin.androidx.compose.koinViewModel
import pro.midev.mec.presentation.ui.style.ArniTheme

class ProfileScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        ProfileScreen(viewModel = koinViewModel())
    }
}

@Composable
private fun ProfileScreen(
    viewModel: ProfileViewModel
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
        ProfileView(
            state = state,
            eventConsumer = viewModel::obtainEvent
        )
    }
}
